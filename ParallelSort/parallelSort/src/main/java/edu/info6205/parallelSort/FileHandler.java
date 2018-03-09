package edu.info6205.parallelSort;

import java.util.ArrayList;

public interface FileHandler<T extends CSVData> {
	public boolean writecsv(String colName,String fileName,ArrayList<T> data);

}
