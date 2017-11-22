package com.wizard.ptcbcs.baseinfo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wizard.ptcbcs.baseinfo.model.ServiceTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IServiceTypeService;
import com.wizard.ptcbcs.result.ResultInfo;
import com.wizard.ptcbcs.result.ResultMessage;

/**
 * 维修类型控制器类
 * @author wizard
 *
 */
@RestController
@RequestMapping(value="/servicetype")
public class ServiceTypeController {
   private IServiceTypeService serviceTypeService;
   @Autowired
   public void setserviceTypeService(IServiceTypeService serviceTypeService) {
		this.serviceTypeService = serviceTypeService;
	}
   /**
    * 增加维修类型
    * @param busType 维修类型类
    * @return ResultMessage 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/add",method=RequestMethod.POST)
   public ResultMessage add(ServiceTypeModel serviceType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("增加维修类型失败");
	   serviceTypeService.add(serviceType);
	   result.setResult("Y");
	   result.setMessage("增加维修类型成功");
	   return result;
	   
	  
   }
   
   



/**
 * 修改维修类型
 * @param busType 维修类型类
 * @return	消息响应类
 * @throws Exception
 */
@RequestMapping(value="/modify",method=RequestMethod.POST)
   public ResultMessage modify(ServiceTypeModel serviceType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("修改维修类型失败");
	   serviceTypeService.modify(serviceType);
	   result.setResult("Y");
	   result.setMessage("修改维修类型成功");
	   return result;
	   
	  
   }
   /**
    * 删除维修类型
    * @param busType 维修类型类
    * @return 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/delete",method=RequestMethod.POST)
   public ResultMessage delete(ServiceTypeModel serviceType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("删除维修类型失败");
	   serviceTypeService.delete(serviceType);
	   result.setResult("Y");
	   result.setMessage("删除维修类型成功");
	   return result;
   }
   /**
    * 按维修类型编号得到维修类型对象
    * @param typeNo 维修类型编号
    * @return 维修类型对象
    * @throws Exception
    */
   @RequestMapping(value="/get",method=RequestMethod.GET)
   public ServiceTypeModel get(@RequestParam int typeNo) throws Exception
   {
	   return serviceTypeService.get(typeNo);
   }
   /**
    * 取得所有维修类型列表，不分页
    * @return 取得所有维修类型列表，不分页
    * @throws Exception
    */
   @RequestMapping(value="/list/all",method=RequestMethod.GET)
   public List<ServiceTypeModel> getListByAll() throws Exception
   {
	   return serviceTypeService.getListByAll();
   }
   /**
    * 取得所有维修类型列表，分页方式
    * @param rows 每页记录数
    * @param page 当前页
    * @return 取得所有维修类型列表，分页方式
    * @throws Exception
    */
   @RequestMapping(value="/list/page",method=RequestMethod.GET)
   public ResultInfo<ServiceTypeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
   {
	   ResultInfo<ServiceTypeModel> result=new ResultInfo<ServiceTypeModel>();
	   result.setCount(serviceTypeService.getCountByAll());
	   result.setPageCount(serviceTypeService.getPageCountByAll(rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(serviceTypeService.getListByAllWithPage(rows, page));
	   
	   return result;
   }
   	/**
   	 * 检查此维修类型能否被删除
   	 * @param typeNo 维修类型编号
   	 * @return 检查此维修类型能否被删除的信息
   	 * @throws Exception
   	 */
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
	public ResultMessage checkCanDelete(@RequestParam int typeNo) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(serviceTypeService.checkCanDelete(typeNo)){
			result.setResult("Y");
			result.setMessage("此维修类型可以删除");
		}
		else{
			result.setResult("N");
			result.setMessage("此维修类型不能删除");
		}
		return result;
	}
	/**
	 * 检查维修类型名称是否存在的信息
	 * @param typeName
	 * @return 检车
	 * @throws Exception
	 */
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam String typeName) throws Exception{
		return !serviceTypeService.checkNameExist(typeName);
		
	}
	/**
	 * 导入维修类型的excel文件
	 * @param importfile excel文件
	 * @return 检查维修类型导入是否成功的信息
	 * @throws Exception
	 */
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 serviceTypeService.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入维修类型成功");
		 }
		 else{
			 result.setResult("N");
			 result.setMessage("没有上传导入Excel文件");
		 }
		 return result;
		
	}
	
	/**
	 * 
	 * @param session 会话
	 * @return 响应体
	 * @throws Exception
	 */
	@RequestMapping(value="/exporttoexcel", method=RequestMethod.GET)
	public ResponseEntity<byte[]> exportToExcel(HttpSession session)throws Exception{
		ServletContext application = session.getServletContext();
		String sourcepath = application.getRealPath("/excelexport/serviceTypeexport.xlsx");
		String exportfilepath = application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		serviceTypeService.exportToExcel(new File(sourcepath), new File(exportfilepath));
		
		String mainType="application";
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("维修类型导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
		FileInputStream fis = new FileInputStream(exportfilepath);
		byte[] data = new byte[fis.available()];
		fis.read(data, 0, data.length);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(new MediaType(mainType,subType));
		File excelFile = new File(exportfilepath);
		excelFile.delete();
		return new ResponseEntity<byte[]>(data,headers,HttpStatus.CREATED);
	}
   
}
