package com.cts.fsd.projectmanager.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parenttask")
public class ParentTask {
	
    private String _id;
    private String parentTask;
		
		public String getParentTask() {
			return parentTask;
		}
		public void setParentTask(String parentTask) {
			this.parentTask = parentTask;
		}
		/**
		 * @return the _id
		 */
		public String get_id() {
			return _id;
		}
		/**
		 * @param _id the _id to set
		 */
		public void set_id(String _id) {
			this._id = _id;
		}
	    
	
}
