package cn.edu.sjtu.dcl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class FileOperation
{
	public static long copyFile(File src,File des) throws IOException
	{
        long time = new Date().getTime();
        int length = 2097152;
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(des);
        FileChannel inC = in.getChannel();
        FileChannel outC = out.getChannel();
        ByteBuffer b=null;
        while(true)
        {
        	if(inC.position() == inC.size())
        	{
                inC.close();
                outC.close();
                return new Date().getTime() - time;
            }
            if((inC.size() - inC.position()) < length)
            {
                length = (int)(inC.size() - inC.position());
            }
            else
                length = 2097152;
            b = ByteBuffer.allocateDirect(length);
            inC.read(b);
            b.flip();
            outC.write(b);
            outC.force(false);
        }
    }
	
	public static void deleteFile(File file)
	{
		if(file.exists())
		{
			if(file.isFile())
			{
				file.delete();
		    }
			else if(file.isDirectory())
			{
				File files[] = file.listFiles();
				for(int i = 0; i < files.length; i++)
				{
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
		else
		{
			System.out.println("所删除的文件不存在！");
		}
	}
}
