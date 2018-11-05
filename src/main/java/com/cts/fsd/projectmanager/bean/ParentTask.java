package com.cts.fsd.projectmanager.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parenttask")
public class ParentTask {
	
    private long parentId;
    private String parentTaskDesc;
		public long getParentId() {
			return parentId;
		}
		public void setParentId(long parentId) {
			this.parentId = parentId;
		}
		public String getParentTaskDesc() {
			return parentTaskDesc;
		}
		public void setParentTaskDesc(String parentTaskDesc) {
			this.parentTaskDesc = parentTaskDesc;
		}
	    
	
}
