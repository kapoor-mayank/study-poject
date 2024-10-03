package org.traccar;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.ws.rs.client.InvocationCallback;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.helper.DataConverter;

public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final long CLEAN_PERIOD = 86400000L;

    private static final String LICENSE_SERVER_MAIN = "http://78.47.219.129/";

    private static final String LICENSE_SERVER_BACKUP = "http://165.227.70.151/";
    private static Injector injector;

    public static Injector getInjector() {
        return injector;
    }

    private static String getHardwareId() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            byte[] address = ((NetworkInterface) interfaces.nextElement()).getHardwareAddress();
            if (address != null) {
                return DatatypeConverter.printHexBinary(address);
            }
        }
        return null;
    }

    private static String getLicenseKey() {
        try {
            return DataConverter.printHex(MessageDigest.getInstance("MD5").
                    digest(String.valueOf(System.currentTimeMillis()).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getLicenseResponse(String key) {
        try {
            return DataConverter.printHex(MessageDigest.getInstance("MD5").digest(key
                    .replaceAll(".(.)?", "$1").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        Context.init(args[0]);
        injector = Guice.createInjector(new Module[]{(Module) new MainModule()});
        LOGGER.info("Starting server...");


        if (Context.getConfig().getBoolean("tpdxsmhywddgptku")) {
            initServerThread();
        } else {
            final String id = getHardwareId();
            final String key = getLicenseKey();
            final String response = getLicenseResponse(key);
            Context.getClient().target("http://78.47.219.129/?id=" + id + "&key=" + key)
                    .request().async().get(new InvocationCallback<String>() {
                        public void completed(String s) {
                            if (s.equals(response)) {
                                Main.initServerThread();
                            } else {
                                Main.LOGGER.warn("License check failed");
                            }
                        }

                        public void failed(Throwable t) {
                            Context.getClient().target("http://165.227.70.151/?id=" + id + "&key=" + key)
                                    .request().async().get(new InvocationCallback<String>() {
                                        public void completed(String s) {
                                            if (s.equals(response)) {
                                                Main.initServerThread();
                                            } else {
                                                Main.LOGGER.warn("License check failed");
                                            }
                                        }

                                        public void failed(Throwable t) {
                                            Main.LOGGER.warn("License check failed", t);
                                        }
                                    });
                        }
                    });
        }
    }

    private static void initServerThread() {
        (new Thread() {
            public void run() {
                try {
                    Main.initServer();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static void initServer() throws Exception {
        Context.getServerManager().start();
        if (Context.getWebServer() != null) {
            Context.getWebServer().start();
        }
        (new Timer()).scheduleAtFixedRate(new TimerTask()
                /*     */ {
            /*     */
            public void run() {
                try {
                    Context.getDataManager().clearHistory();
                } catch (SQLException error) {
                    Main.LOGGER.warn("Clear history error", error);
                }
            }
            /*     */
        }, 0L, 86400000L);
        Runtime.getRuntime().addShutdownHook(new Thread()
                /*     */ {
            /*     */
            public void run() {
                Main.LOGGER.info("Shutting down server...");
                if (Context.getWebServer() != null) {
                    Context.getWebServer().stop();
                }
                Context.getServerManager().stop();
            }
            /*     */
        });
    }
}
