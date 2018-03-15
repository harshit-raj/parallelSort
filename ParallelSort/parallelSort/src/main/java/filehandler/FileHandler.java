package filehandler;

import java.util.ArrayList;

import main.CSVData;

public interface FileHandler<T extends CSVData> {
	public boolean writecsv(String colName,String fileName,ArrayList<T> data);

}
