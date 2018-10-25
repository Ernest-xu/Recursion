package Tokendemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Recursion {
	/**
	 * 获取层级的递归方法
	 * 
	 * @param list 要处理的数据List
	 * @param map 返回的数据key:返回的名称value：数据的名称
	 * @param Parentmap 父子关联key：父value：子
	 * @param number 填1对应obj为null,大于1时填Parentmap当前关联的子一般为当前行主键
	 * @param tier 与number的差值为层数(注：tier>number)
	 * @param obj 当前层的关联（子）和number有关
	 * @return 返回List
	 * 
	 */
	 
	public static List<Map<String, Object>> getChildren(List<Map<String, Object>> list,Map<String ,String> map,Map<String ,String> Parentmap,int number,int tier,Object obj){
		List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		int number1=number+1;
		boolean falge = true;
		if(list==null){
			falge=false;
		}
		if(map==null){
			falge=false;
		}
		if(Parentmap==null){
			falge=false;
		}
		if(number<0){
			falge=false;
		}
		if(tier<0){
			falge=false;
		}
		if(number>tier){
			falge=false;
		}
		if(falge){
			Object ParentID=null;
			Object ID = null;
			String a=null;
			for(String key:Parentmap.keySet()){
				a=key;
			}
			for(int i = 0;i<list.size();i++){
				Map<String ,Object> map2 = new HashMap<String, Object>();
					for(String key : map.keySet()){
						map2.put(key,list.get(i).get(map.get(key)));
					}
					ParentID=map2.get(a);
					ID = map2.get(Parentmap.get(a));
				if(number==1){
					
					if(ParentID==null||"".equals(ParentID)){
						List<Map<String, Object>> children=null;
						Map<String,Object> map1 = new HashMap<String, Object>(); 
						if(tier>=number1){
							children = getChildren(list,map,Parentmap,number1,tier,ID);
						}
						for(String key : map.keySet()){
							if("sName".equals(key)){
								map1.put(key,map2.get(key)==null?"":map2.get(key));
							}else{
								map1.put(key,map2.get(key));
							}
						}
						map1.put("children", children);
						list1.add(map1);
					}
				}else{
					
					if(obj.equals(ParentID)){
						Map<String,Object> map1 = new HashMap<String, Object>(); 
						List<Map<String, Object>> children=null;
						if(tier>=number1){
							children = getChildren(list,map,Parentmap,number1,tier,ID);
						}
						
						for(String key : map.keySet()){
							if("sName".equals(key)){
								map1.put(key,map2.get(key)==null?"":map2.get(key));
							}else{
								map1.put(key,map2.get(key));
							}
						}
						map1.put("children", children);
						list1.add(map1);
					}
				}
				
			};
		}
		
		return list1;
	}; 
	
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = null;
		List<Map<String, Object>> list3 = null;
			
		String json = "[{\"isShow\": \"1\",\"sID\": \"bd9513c9-c182-4fe4-a9e3-4ae897ea23d5\",\"sName\": \"项目看板\",\"sType\": \"logStatistics\"},"
		+"{\"isShow\": \"1\",\"sID\": \"0bf21224-70a6-42eb-9bf7-943f404985af\",\"sName\": \"实时数据\",\"sType\": \"logStatistics\"},"
		+"{\"isShow\": \"1\",\"sID\": \"ee05f9db-48f8-4a95-a745-d7bab9fd3e4d\",\"sName\": \"站班日志\",\"sType\": \"logStatistics\"},"
		+"{\"isShow\": \"1\",\"sID\": \"d3323025-8c70-48df-a8a5-15fa9de37976\",\"sName\": \"进度日志\",\"sType\": \"logStatistics\"},"
		+"{\"isShow\": \"1\",\"sID\": \"7eaf5a05-1336-4806-927a-5eb1a8e32d05\", \"sName\": \"质量日志\",\"sType\": \"logStatistics\"},"
		+"{\"isShow\": \"1\",\"sID\": \"5c476307-5cfc-4eaf-a52b-b33189a75344\",\"sName\": \"安全日志\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\",\"sID\": \"8af7ea61-d305-4b2d-9ea6-f79bc65094c4\", \"sName\": \"质量统计\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\",\"sID\": \"85eb9dec-26ce-41be-80f3-7886b85fab01\",\"sName\": \"安全统计\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\", \"sID\": \"3ed5f056-ea86-4730-a75e-14b2eac22deb\",\"sName\": \"履职日志\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\",\"sID\": \"4f8d23ae-a8eb-4ace-82b9-8f006ef8f0f1\",\"sName\": \"风险日志\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\",\"sID\": \"8e2eaf53-4d23-467b-9ee1-b66494044f26\",\"sName\": \"交底日志\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\",\"sID\": \"69952f53-bd2c-4910-930e-f3f14b8549a5\",\"sName\": \"工程罗盘\",\"sType\": \"logStatistics\"},"
        +"{\"isShow\": \"1\", \"sID\": \"eee9ca99-9a70-439f-823b-98c4a74a8e53\",\"sName\": \"项目立项\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"0\",\"sID\": \"d5a4a529-2c69-4fc5-b6fc-26c84107ec5e\",\"sName\": \"任务分配\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"0\",\"sID\": \"a632369b-4ecb-4b68-9084-771de647fa7f\",\"sName\": \"任务清单\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"979af211-8e58-40d8-8cd4-6852e0ae6c2e\",\"sName\": \"现场监控\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"4f383ad2-76a3-4a8b-811c-c520803bb92f\", \"sName\": \"文档中心\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"644b8969-244a-4518-95db-ff8ecec2faec\",\"sName\": \"公告通知\",\"sType\": \"engineeringManager\" },"
        +"{\"isShow\": \"1\",\"sID\": \"6aa80884-6735-4be6-8bb7-148bcc3b6d32\",\"sName\": \"公告文档\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\", \"sID\": \"aba03c20-f5c7-45eb-b725-6e132f8d2ba5\",\"sName\": \"签到打卡\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"f18bdd52-4475-4cbe-97b7-ccd436493fd9\",\"sName\": \"日排行榜\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"beaf1470-9cf1-49d6-bbe0-915c14cd4d36\",\"sName\": \"月度考勤\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"3baa4024-508d-4e6b-8140-cdf05e347289\",\"sName\": \"人脸注册\",\"sType\": \"engineeringManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"9b4fbf13-0cbb-48f0-8f7d-a39fb5c437b1\",\"sName\": \"照片管理\",\"sType\": \"engineeringManager\"},"
	    +"{\"isShow\": \"1\",\"sID\": \"803220d1-effa-4d6c-9fb8-acc6286eb265\",\"sName\": \"人脸签到\", \"sType\": \"engineeringManager\"},"
	    +"{\"isShow\": \"1\",\"sID\": \"42c12eb0-0d6e-4602-9d51-55916b66bb02\",\"sName\": \"站班汇报\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"1\",\"sID\": \"037c9f01-a7ac-4e13-b165-7aa71e077878\",\"sName\": \"进度汇报\", \"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"1\",\"sID\": \"91273e3d-33be-407a-936b-2b18b7c5a7d6\",\"sName\": \"质量检查\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"0\",\"sID\": \"50fbf330-3c36-4641-b15e-2731b9d76a85\",\"sName\": \"安全检查\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"1\",\"sID\": \"f012d0db-1a70-406f-b2c5-f176db2e7ba3\",\"sName\": \"签到打卡\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"0\",\"sID\": \"e008d1ef-31f4-4a60-a47d-9b73c85338a1\",\"sName\": \"工作履职\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"1\",\"sID\": \"d1405753-b9a7-4185-aa0f-389b4295231c\",\"sName\": \"风险管控\",\"sType\": \"reportFunctions\" },"
        +"{\"isShow\": \"1\",\"sID\": \"1e5db8e0-bdab-4939-b1d2-109913224e3a\",\"sName\": \"施工交底\",\"sType\": \"reportFunctions\"},"
        +"{\"isShow\": \"1\",\"sID\": \"dd2026e7-649e-4ff6-a60b-84392a4720cb\",\"sName\": \"角色管理\",\"sType\": \"systemManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"ffb6177f-b8e9-40e2-95cb-ae2d5305eab3\",\"sName\": \"授权管理\",\"sType\": \"systemManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"a098689a-f987-4226-8926-01f4f4970b34\",\"sName\": \"岗位管理\",\"sType\": \"systemManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"d6945c35-3bac-4869-9a45-223ec1fb3653\",\"sName\": \"安全标准\",\"sType\": \"systemManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"89bfba96-e16f-4ede-87df-654d3f0d9359\",\"sName\": \"质量标准\",\"sType\": \"systemManager\"},"
        +"{\"isShow\": \"1\",\"sID\": \"251e97a9-05be-4db1-bd6b-4cf58b9c972e\",\"sName\": \"工作票配置\",\"sType\": \"systemManager\"}"
        +"]";
        
		List<Object> list1 =JSON.parseArray(json);
        List< Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
        for (Object object : list1){
        Map<String,Object> ageMap = new HashMap<String,Object>();
        Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
        list2.add(ret);
        }
		
		list3 = getType();
		if(list3!=null){
			for(int i=0;i<list3.size();i++){
				Map<String,Object> map = list3.get(i);
				list2.add(map);
			}
		}

		//层级目录展示的字段
		Map<String,String > fieldmap = new HashMap<String, String>();
		fieldmap.put("sID", "sID");
		fieldmap.put("sName", "sName");
		fieldmap.put("sParentID","sParentID");
		fieldmap.put("sType", "sType");
		fieldmap.put("isShow", "isShow");
		//父子关联关系key父，sParentID子（父与子是对应谁下面的）
		Map<String,String > Parentmap = new HashMap<String, String>();
		Parentmap.put("sType", "sParentID");
		list = getChildren(list2,fieldmap,Parentmap,1,2,null);
		System.out.println(list.toString());
		
		
		
	}
	
	
	/**
	 * 权限类别
	 * @return
	 */
	public static List<Map<String, Object>> getType(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("sName", "工程管理");
		map1.put("sParentID", "engineeringManager");
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("sName", "日志统计");
		map2.put("sParentID", "logStatistics");
		Map<String,Object> map3 = new HashMap<String, Object>();
		map3.put("sName", "汇报模块");
		map3.put("sParentID", "reportFunctions");
		Map<String,Object> map4 = new HashMap<String, Object>();
		map4.put("sName", "系统管理");
		map4.put("sParentID", "systemManager");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		return list;
	};
}
