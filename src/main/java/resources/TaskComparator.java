package main.java.resources;
//@Jiahuan

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.java.logic.Sort;

//comparator that compare iscomplete first then time, then task content string
	public class TaskComparator implements  Comparator<Sort>{

		@Override
		public int compare(Sort sort1, Sort sort2) {
			int completeResult = sort1.getIsComplete().compareTo(sort2.getIsComplete());
			if (completeResult != 0){
				return completeResult;
			}
			/*int timeResult = sort1.getTaskTime().compareTo(sort2.getTaskTime());
			if (timeResult != 0){
				return timeResult;
			}*/
			int stringResult = sort1.getTaskContent().compareTo(sort2.getTaskContent());
			return stringResult;
		}
		
		/*public static void main(String arg[]){
			ArrayList<Task> sortResult = new ArrayList<Task> ();
			ArrayList<Sort> sortList = createSortList(listForSort);
			Collections.sort(sortList, new TaskComparator());
			for (int i = 0; i <sortList.size(); i++){
				sortResult.add(sortList.get(i).getTask());
			}
		}*/
		
	}