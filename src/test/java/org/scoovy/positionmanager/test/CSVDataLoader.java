package org.scoovy.positionmanager.test;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
@Component
public class CSVDataLoader extends AbstractDataSetLoader{
	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		return new CsvURLDataSet(resource.getURL());
	}
}
