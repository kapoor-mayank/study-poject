/*     */
package org.traccar;
/*     */
/*     */

import com.google.inject.Guice;
/*     */ import com.google.inject.Injector;
/*     */ import com.google.inject.Module;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Locale;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import javax.ws.rs.client.InvocationCallback;
/*     */ import javax.xml.bind.DatatypeConverter;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.helper.DataConverter;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public final class Main
        /*     */ {
    /*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    /*     */
    /*     */   private static final long CLEAN_PERIOD = 86400000L;
    /*     */
    /*     */   private static final String LICENSE_SERVER_MAIN = "http://78.47.219.129/";
    /*     */
    /*     */   private static final String LICENSE_SERVER_BACKUP = "http://165.227.70.151/";
    /*     */   private static Injector injector;

    /*     */
    /*     */
    public static Injector getInjector() {
        /*  48 */
        return injector;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    private static String getHardwareId() throws SocketException {
        /*  55 */
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        /*  56 */
        while (interfaces.hasMoreElements()) {
            /*  57 */
            byte[] address = ((NetworkInterface) interfaces.nextElement()).getHardwareAddress();
            /*  58 */
            if (address != null) {
                /*  59 */
                return DatatypeConverter.printHexBinary(address);
                /*     */
            }
            /*     */
        }
        /*  62 */
        return null;
        /*     */
    }

    /*     */
    /*     */
    private static String getLicenseKey() {
        /*     */
        try {
            /*  67 */
            return DataConverter.printHex(MessageDigest.getInstance("MD5").digest(
                    /*  68 */             String.valueOf(System.currentTimeMillis()).getBytes()));
            /*  69 */
        } catch (NoSuchAlgorithmException e) {
            /*  70 */
            throw new RuntimeException(e);
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    private static String getLicenseResponse(String key) {
        /*     */
        try {
            /*  76 */
            return DataConverter.printHex(MessageDigest.getInstance("MD5").digest(key
/*  77 */.replaceAll(".(.)?", "$1").getBytes()));
            /*  78 */
        } catch (NoSuchAlgorithmException e) {
            /*  79 */
            throw new RuntimeException(e);
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    public static void main(String[] args) throws Exception {
        /*  84 */
        Locale.setDefault(Locale.ENGLISH);
        /*     */
        /*  86 */
        Context.init(args[0]);
        /*  87 */
        injector = Guice.createInjector(new Module[]{(Module) new MainModule()});
        /*  88 */
        LOGGER.info("Starting server...");
        /*     */
        /*  90 */
        if (Context.getConfig().getBoolean("tpdxsmhywddgptku")) {
            /*  91 */
            initServerThread();
            /*     */
        } else {
            /*  93 */
            final String id = getHardwareId();
            /*  94 */
            final String key = getLicenseKey();
            /*  95 */
            final String response = getLicenseResponse(key);
            /*  96 */
            Context.getClient().target("http://78.47.219.129/?id=" + id + "&key=" + key)
/*  97 */.request().async().get(new InvocationCallback<String>()
                    /*     */ {
                /*     */
                public void completed(String s) {
                    /* 100 */
                    if (s.equals(response)) {
                        /* 101 */
                        Main.initServerThread();
                        /*     */
                    } else {
                        /* 103 */
                        Main.LOGGER.warn("License check failed");
                        /*     */
                    }
                    /*     */
                }

                /*     */
                /*     */
                /*     */
                public void failed(Throwable t) {
                    /* 109 */
                    Context.getClient().target("http://165.227.70.151/?id=" + id + "&key=" + key)
/* 110 */.request().async().get(new InvocationCallback<String>()
                            /*     */ {
                        /*     */
                        public void completed(String s) {
                            /* 113 */
                            if (s.equals(response)) {
                                /* 114 */
                                Main.initServerThread();
                                /*     */
                            } else {
                                /* 116 */
                                Main.LOGGER.warn("License check failed");
                                /*     */
                            }
                            /*     */
                        }

                        /*     */
                        /*     */
                        /*     */
                        public void failed(Throwable t) {
                            /* 122 */
                            Main.LOGGER.warn("License check failed", t);
                            /*     */
                        }
                        /*     */
                    });
                    /*     */
                }
                /*     */
            });
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    private static void initServerThread() {
        /* 131 */
        (new Thread()
                /*     */ {
            /*     */
            public void run() {
                /*     */
                try {
                    /* 135 */
                    Main.initServer();
                    /* 136 */
                } catch (Exception e) {
                    /* 137 */
                    throw new RuntimeException(e);
                    /*     */
                }
                /*     */
            }
            /* 140 */
        }).start();
        /*     */
    }

    /*     */
    /*     */
    private static void initServer() throws Exception {
        /* 144 */
        Context.getServerManager().start();
        /* 145 */
        if (Context.getWebServer() != null) {
            /* 146 */
            Context.getWebServer().start();
            /*     */
        }
        /*     */
        /* 149 */
        (new Timer()).scheduleAtFixedRate(new TimerTask()
                /*     */ {
            /*     */
            public void run() {
                /*     */
                try {
                    /* 153 */
                    Context.getDataManager().clearHistory();
                    /* 154 */
                } catch (SQLException error) {
                    /* 155 */
                    Main.LOGGER.warn("Clear history error", error);
                    /*     */
                }
                /*     */
            }
            /*     */
        }, 0L, 86400000L);
        /*     */
        /* 160 */
        Runtime.getRuntime().addShutdownHook(new Thread()
                /*     */ {
            /*     */
            public void run() {
                /* 163 */
                Main.LOGGER.info("Shutting down server...");
                /*     */
                /* 165 */
                if (Context.getWebServer() != null) {
                    /* 166 */
                    Context.getWebServer().stop();
                    /*     */
                }
                /* 168 */
                Context.getServerManager().stop();
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */