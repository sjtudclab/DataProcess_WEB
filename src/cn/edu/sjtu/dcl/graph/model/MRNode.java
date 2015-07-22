package cn.edu.sjtu.dcl.graph.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MRNode extends Vertex
{
	private long jobid;
	private String path;
	private String mapper;
	private String reducer;
	private String inputformat;
	private String mapinputkey;
	private String mapinputvalue;
	private String mapoutputkey;
	private String mapoutputvalue;
	private String outputKey;
	private String outputValue;
	private String inputPath;
	private String outputPath;
	private HashMap<String, String> parameters;
	private boolean isIterative;
	private int iterationtimes;
	private String to;
	
	public MRNode(String name)
	{
		setName(name);
		setType(0);
		parameters = new HashMap<String, String>();
	}
	
	public long getJobId()
	{
		return jobid;
	}
	public void setJobId(long jobid)
	{
		this.jobid = jobid;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getMapper()
	{
		return mapper;
	}
	public void setMapper(String mapper)
	{
		this.mapper = mapper;
	}
	public String getReducer()
	{
		return reducer;
	}
	public void setReducer(String reducer)
	{
		this.reducer = reducer;
	}
	public String getInputformat()
	{
		return inputformat;
	}
	public void setInputformat(String inputformat)
	{
		this.inputformat = inputformat;
	}
	public String getMapinputkey()
	{
		return mapinputkey;
	}
	public void setMapinputkey(String mapinputkey)
	{
		this.mapinputkey = mapinputkey;
	}
	public String getMapinputvalue()
	{
		return mapinputvalue;
	}
	public void setMapinputvalue(String mapinputvalue)
	{
		this.mapinputvalue = mapinputvalue;
	}
	public String getMapoutputkey()
{
		return mapoutputkey;
	}
	public void setMapoutputkey(String mapoutputkey) 
	{
		this.mapoutputkey = mapoutputkey;
	}
	public String getMapoutputvalue()
	{
		return mapoutputvalue;
	}
	public void setMapoutputvalue(String mapoutputvalue)
	{
		this.mapoutputvalue = mapoutputvalue;
	}
	public String getOutputKey()
	{
		return outputKey;
	}
	public void setOutputKey(String outputKey)
	{
		this.outputKey = outputKey;
	}
	public String getOutputValue()
	{
		return outputValue;
	}
	public void setOutputValue(String outputValue)
	{
		this.outputValue = outputValue;
	}
	public HashMap<String, String> getParameters()
	{
		return parameters;
	}
	public String[] getPara()
	{
		String[] p = new String[parameters.size()];
		Set<String> para = parameters.keySet();
		int i = 0;
		Iterator<String> iter = para.iterator();
		while(iter.hasNext())
		{
			p[i++] = iter.next();
		}
		return p;
	}

	public boolean isIterative()
	{
		return isIterative;
	}

	public void setIterative(boolean isIterative)
	{
		this.isIterative = isIterative;
	}

	public String getInputPath()
	{
		return inputPath;
	}

	public void setInputPath(String inputPath)
	{
		this.inputPath = inputPath;
	}

	public String getOutputPath()
	{
		return outputPath;
	}
	public void setOutputPath(String outputPath)
	{
		this.outputPath = outputPath;
	}
	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public int getIterationtimes()
	{
		return iterationtimes;
	}

	public void setIterationtimes(int iterationtimes)
	{
		this.iterationtimes = iterationtimes;
	}
}
