package silence.common.convertor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

/**
 * 完成字符串到Timestamp的类型转换器
 * @author 牟勇
 *
 */
public class StringToDateConvertor implements Converter<String, Timestamp> {
	private String[] datePattners;
	
	
	public StringToDateConvertor(String[] datePattners) {
		this.datePattners = datePattners;
	}


	@Override
	public Timestamp convert(String stringDate) {
		
		
			for(String datePattner:datePattners){
				//如果不能转，则尝试使用下一个。
				try {
					return new Timestamp(new SimpleDateFormat(datePattner).parse(stringDate).getTime());
				} catch (ParseException e) {
					
				}
			}
		//如果所有格式都不能转，则返回一个null
		return null;
	}

}
