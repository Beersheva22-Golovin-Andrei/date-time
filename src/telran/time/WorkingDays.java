package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class WorkingDays implements TemporalAdjuster {
	
	private DayOfWeek[] dayOffs;
	
	int workingDays;
	
		@Override
		public Temporal adjustInto(Temporal temporal) {
			 DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK)); 
			 int firstDayOffs = (int)Arrays.stream(dayOffs).filter(day -> dayOfWeek.getValue()<day.getValue()).count();
			 int allDays = ((int)(workingDays/7))* dayOffs.length + firstDayOffs + workingDays;
			return temporal.plus(allDays, ChronoUnit.DAYS);
		}
		
		public WorkingDays(DayOfWeek[] dayOffs, int workingDays) {
			this.dayOffs = dayOffs;
			this.workingDays = workingDays;
		}
}