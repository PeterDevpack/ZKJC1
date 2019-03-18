package com.unitop.util.ad;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.unitop.bank.CommonUtil;




public class ADUtil {

public static DirContext ctx;
	
	public static void connect(){
		try{
			if(ctx==null){
				String ip = "ldap\://gluetest.systems.uk.hsbc\:3268";
				String bindDn = "CN=43387940,OU=HSBCPeople,DC=InfoDir,DC=Dev,DC=HSBC";
				String password = "G%kkdd>$8NtjTV2d";
				
				Hashtable<String, String> env = new Hashtable<String, String>();
		        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		        env.put(Context.PROVIDER_URL, ip);
		        env.put(Context.SECURITY_AUTHENTICATION, "simple");
		        env.put(Context.SECURITY_PRINCIPAL, bindDn);
		        env.put(Context.SECURITY_CREDENTIALS, password);
		        

		        ctx = new InitialDirContext(env);

			}
	    }catch(Exception e){
	         e.printStackTrace();
	    }
	}
	
	public List<String> Search(String userId){
		List<String> list = new ArrayList<String>();
		SearchControls searchControls = new SearchControls();
	    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    searchControls.setTimeLimit(0);
	    try{
	    	connect();
	    	String expression = getExpression(userId);
	    	NamingEnumeration<?> namingEnum = ctx.search("OU=HSBCPeople,DC=InfoDir,DC=Dev,DC=HSBC", expression, searchControls);
	    	while (namingEnum.hasMore()) {
	    		SearchResult result = (SearchResult) namingEnum.next();
	    		Attributes Attrs = result.getAttributes();
	    		Attribute memberofs = Attrs.get("memberof");
	        	if(memberofs!=null){
	        		CommonUtil.info(" memberof ID:"+ memberofs.getID().toString());  
	        		for (NamingEnumeration<?> ne = memberofs.getAll(); ne.hasMore();) {  
	        			String memberof = ne.next().toString();
	        			CommonUtil.info("memberof Value:"+ memberof);
	        			String countryInstitution = getCountryInstitution(memberof);
	        			if(countryInstitution!=null){
	        				list.add(countryInstitution);
	        				CommonUtil.info("CountryInstitution:"+countryInstitution);
	        			}
	        		}
	        	}
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }finally{
			try {
				if(ctx!=null){
					ctx.close();
					ctx = null;
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	    return list;
	}
	
	public String getExpression(String userId){
		String ldapExpression = "(|(employeeID={1})(hsbc-ad-SAMAccountName={1}))";
		ldapExpression = ldapExpression.replace("{1}", userId);
		return ldapExpression;
	}
	
	public String getCountryInstitution(String memberof){
		if(memberof!=null){
			int index = memberof.indexOf("CN=InfoDir-GMC-");
			int index_end = memberof.indexOf("-User");
			if(index!=-1&&index_end!=-1){
				int length = "CN=InfoDir-GMC-".length();
				return memberof.substring(index+length,index_end).replaceAll("-", "");
			}
		}
		return null;
	}
	
	public String getCountryInstitutionHK(String memberof){
		if(memberof!=null){
			int index = memberof.indexOf("CN=InfoDir-GMC-");
			if(index!=-1){
				int length = "CN=InfoDir-GMC-".length();
				return memberof.substring(index+length,index+length+7).replaceAll("-", "");
			}
		}
		return null;
	}
	
}
