/*    */ package org.traccar.model;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import net.fortuna.ical4j.data.CalendarBuilder;
/*    */ import net.fortuna.ical4j.data.ParserException;
/*    */ import net.fortuna.ical4j.filter.Filter;
/*    */ import net.fortuna.ical4j.filter.PeriodRule;
/*    */ import net.fortuna.ical4j.model.DateTime;
/*    */ import net.fortuna.ical4j.model.Dur;
/*    */ import net.fortuna.ical4j.model.Period;
/*    */ import net.fortuna.ical4j.model.component.CalendarComponent;
/*    */ import org.apache.commons.collections4.Predicate;
/*    */ import org.traccar.database.QueryIgnore;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Calendar
/*    */   extends ExtendedModel
/*    */ {
/*    */   private String name;
/*    */   private byte[] data;
/*    */   private net.fortuna.ical4j.model.Calendar calendar;
/*    */   
/*    */   public String getName() {
/* 42 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 46 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 52 */     return (byte[])this.data.clone();
/*    */   }
/*    */   
/*    */   public void setData(byte[] data) throws IOException, ParserException {
/* 56 */     CalendarBuilder builder = new CalendarBuilder();
/* 57 */     this.calendar = builder.build(new ByteArrayInputStream(data));
/* 58 */     this.data = (byte[])data.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @QueryIgnore
/*    */   @JsonIgnore
/*    */   public net.fortuna.ical4j.model.Calendar getCalendar() {
/* 66 */     return this.calendar;
/*    */   }
/*    */   
/*    */   public boolean checkMoment(Date date) {
/* 70 */     if (this.calendar != null) {
/* 71 */       Period period = new Period(new DateTime(date), new Dur(0, 0, 0, 0));
/* 72 */       PeriodRule periodRule = new PeriodRule(period);
/* 73 */       Filter<CalendarComponent> filter = new Filter(new Predicate[] { (Predicate)periodRule }, 1);
/* 74 */       Collection<CalendarComponent> events = filter.filter((Collection)this.calendar.getComponents("VEVENT"));
/* 75 */       if (events != null && !events.isEmpty()) {
/* 76 */         return true;
/*    */       }
/*    */     } 
/* 79 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Calendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */