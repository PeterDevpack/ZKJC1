package zkjc.test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Properties env = new Properties();
		// 使用UPN格式：User@domain或SamAccountName格式：domain\\User
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		String adminName = "administrator@moonxy.com";
		String adminPassword = "smartdot&2014";// password
		String ldapURL = "LDAP://133.10.221.122:22";// ip:port

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");// LDAP访问安全级别："none","simple","strong"
		env.put(Context.SECURITY_PRINCIPAL, adminName);// AD User
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);// AD Password
		env.put(Context.PROVIDER_URL, ldapURL);// LDAP工厂类
		System.out.println("11");
		try {
			/*LdapContext ctx = new InitialLdapContext(env, null);
			// 搜索控制器
			SearchControls searchCtls = new SearchControls();
			// 创建搜索控制器
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// LDAP搜索过滤器类，此处只获取AD域用户，所以条件为用户user或者person均可
			// (&(objectCategory=person)(objectClass=user)(name=*))
			String searchFilter = "objectClass=user";
			// AD域节点结构
			String searchBase = "OU=Java开发组,OU=软件研发部,DC=moonxy,DC=com";

			String returnedAtts[] = { "url", "employeeID", "mail", "name",
					"userPrincipalName", "physicalDeliveryOfficeName",
					"departmentNumber", "telephoneNumber", "homePhone",
					"mobile", "department", "sAMAccountName", "whenChanged" }; // 定制返回属性
			searchCtls.setReturningAttributes(returnedAtts);
			NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);*/
			
			DirContext ctx = new InitialDirContext(env);
			System.out.println("1111");
			List<String> list = new ArrayList<String>();
			SearchControls searchControls = new SearchControls();
		    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		    searchControls.setTimeLimit(0);
		    System.out.println("111");
		    NamingEnumeration<?> answer = ctx.search("000000", null, searchControls);
			System.out.println("22222");
			
			while (answer.hasMoreElements()) {
				System.out.println("33");
				
				SearchResult sr = (SearchResult) answer.next();
				System.out.println("<<<::[" + sr.getName() + "]::>>>>");// 返回格式一般是CN=xxxx,OU=xxxx
				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
				if (Attrs != null) {
					for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
						Attribute Attr = (Attribute) ne.next();// 得到下一个属性
						System.out.print(Attr.getID().toString() + ":");
						// 读取属性值
						for (NamingEnumeration e = Attr.getAll(); e.hasMore();) {
							String userInfo = e.next().toString();
							System.out.print(userInfo);
						}
						System.out.println("111");
					}
				}
			}
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Problem searching directory: " + e);
		}
	}

}
