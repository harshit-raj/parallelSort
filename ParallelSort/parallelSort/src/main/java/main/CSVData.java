package main;

public class CSVData {
	private long size;
	private double runtime;
	private int cutoff;
	
	
	
	
	public CSVData() {
		
	}
	
	
	
	public CSVData(long size, double runtime, int cutoff) {
		super();
		this.size = size;
		this.runtime = runtime;
		this.cutoff = cutoff;
	}



	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public double getRuntime() {
		return runtime;
	}
	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}
	public int getCutoff() {
		return cutoff;
	}
	public void setCutoff(int cutoff) {
		this.cutoff = cutoff;
	}



	@Override
	public String toString() {
		return "Data [size=" + size + ", runtime=" + runtime + ", cutoff=" + cutoff + "]";
	}
	
	public String toFile() {
		return size+","+runtime+","+cutoff+"\n";
	}
	
	
	
	

}
