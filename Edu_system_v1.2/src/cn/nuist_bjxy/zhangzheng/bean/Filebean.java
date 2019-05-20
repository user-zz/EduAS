package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 存储文件的数据
 * @date 下午8:47:07
 */

public class Filebean {
	
	private int fno; //编号
	private String fusername; //上传者
	private String  fname; //文件名
	private String fpath; //文件地址
	private String ftype; //文件类别
	private String downloadcount; //点击量
	private String ftime; //操作时间
	private double fsize; //文件大小
	private String fid; //文件id
	private String fpass = "F"; // 文件审核标识,默认值为F，表示未通过
	
	
	public String getFpass() {
		return fpass;
	}
	public void setFpass(String fpass) {
		this.fpass = fpass;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public double getFsize() {
		return fsize;
	}
	public void setFsize(double fsize) {
		this.fsize = fsize;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(String downloadcount) {
		this.downloadcount = downloadcount;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	
}

