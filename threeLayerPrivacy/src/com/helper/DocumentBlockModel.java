package com.helper;

public class DocumentBlockModel {
String blockid, docId, userid, blockSHA, referenceBlock, userDocId;
public String getBlockSHA() {
	return blockSHA;
}

public void setBlockSHA(String blockSHA) {
	this.blockSHA = blockSHA;
}

public String getReferenceBlock() {
	return referenceBlock;
}

public void setReferenceBlock(String referenceBlock) {
	this.referenceBlock = referenceBlock;
}

public String getUserDocId() {
	return userDocId;
}

public void setUserDocId(String userDocId) {
	this.userDocId = userDocId;
}

byte [] blockDoc;

public byte[] getBlockDoc() {
	return blockDoc;
}

public void setBlockDoc(byte[] blockDoc) {
	this.blockDoc = blockDoc;
}

public String getBlockid() {
	return blockid;
}

public void setBlockid(String blockid) {
	this.blockid = blockid;
}



public String getDocId() {
	return docId;
}

public void setDocId(String docId) {
	this.docId = docId;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}
}
