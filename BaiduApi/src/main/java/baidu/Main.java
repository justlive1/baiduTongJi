package baidu;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import baidu.model.DataServiceAcceptData;
import baidu.model.DoLoginAcceptData;
import baidu.model.DoLogoutAcceptData;
import baidu.model.PreLoginAcceptData;
import baidu.model.ProfileSitesAcceptData;
import baidu.model.ReportQueryAcceptData;
import baidu.model.ReportQueryTransmitData;
import baidu.model.ReportStatusAcceptData;
import baidu.service.BaiduStatisticsService;

public class Main {

	private static AbstractApplicationContext ctx;

	public static void main(String[] args) {

		ctx = new ClassPathXmlApplicationContext("classpath*:/beans/**.xml");

		ctx.registerShutdownHook();

		BaiduStatisticsService secService = ctx.getBean(BaiduStatisticsService.class);
		PreLoginAcceptData preData = secService.preLogin();
		DoLoginAcceptData loginData = secService.doLogin(preData);
		System.out.println(loginData);

		DataServiceAcceptData<ProfileSitesAcceptData> obj = secService.getSites(loginData.getSt(),
				loginData.getUcid().toString());

		Integer siteid = obj.getBody().getResponseData().getSites()[0].getSiteid();
		System.out.println();

//		ProfileQueryTramsmitData query = new ProfileQueryTramsmitData();
//		query.setName("%");
//		DataServiceAcceptData<ProfileTransInfoAcceptData> obj1 = secService.getTransInfo(loginData.getSt(),
//				loginData.getUcid().toString(), query);
//
//		System.out.println(obj1);

		ReportQueryTransmitData query2 = new ReportQueryTransmitData();
		query2.setReportid(1);
		query2.setSiteid(siteid);
		query2.setMetrics(new String[]{"pageviews","visitors","ips","exitRate"});
		query2.setDimensions(new String[]{"pageid"});
		query2.setStart_time("20160218000000");
		query2.setEnd_time("20160618000000");
		query2.setFilters(new String[]{"fromType=2","newvisitor=1"});
		query2.setStart_index(0);
		query2.setMax_result(100);
		query2.setSort(new String[]{"pageviews desc","exitRate desc"});
		
		DataServiceAcceptData<ReportQueryAcceptData> obj2 = secService.getReportQuery(loginData.getSt(),loginData.getUcid().toString(), query2);
		
		System.out.println(obj2);
		
		DataServiceAcceptData<ReportStatusAcceptData> obj3 = secService.getReportStatus(loginData.getSt(),loginData.getUcid().toString(), "");
		
		System.out.println(obj3);
		
		DoLogoutAcceptData res = secService.logout(loginData);

		System.out.println(res);
	}
}
