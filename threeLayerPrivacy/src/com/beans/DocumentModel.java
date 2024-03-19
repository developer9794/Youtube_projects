package com.beans;

public class DocumentModel {//docDate, docUid, docLastUid
	String docId, docName, docSize, docDate, docUid, docSum, docAccess, docLastUid , c;
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	byte[] docData;
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	public byte[] getDocData() {
		return docData;
	}

	public void setDocData(byte[] docData) {
		this.docData = docData;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getDocUid() {
		return docUid;
	}

	public void setDocUid(String docUid) {
		this.docUid = docUid;
	}

	public String getDocSum() {
		return docSum;
	}

	public void setDocSum(String docSum) {
		this.docSum = docSum;
	}

	public String getDocAccess() {
		return docAccess;
	}

	public void setDocAccess(String docAccess) {
		this.docAccess = docAccess;
	}

	public String getDocLastUid() {
		return docLastUid;
	}

	public void setDocLastUid(String docLastUid) {
		this.docLastUid = docLastUid;
	}


	
}
