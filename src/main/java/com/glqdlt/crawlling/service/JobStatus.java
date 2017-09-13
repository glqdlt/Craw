package com.glqdlt.crawlling.service;


public class JobStatus {

	private JobStatus() {
		status = 0;
	}

	private static JobStatus ins;

	private Integer status;

	public synchronized Integer getStatus() {
		return status;
	}

	public synchronized void setStatus(Integer status) {
		this.status = status;
	}

	public static JobStatus getInstance() {
		if (ins == null) {
			ins = new JobStatus();
		}

		return ins;
	}

}
