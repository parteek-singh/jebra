package example;

public class getpaidFile {/*


	
	
	@Inject CustomerServiceLocal  customerService ;
	@Inject CustomerConversionService conversionService;
	@Inject PortfolioServiceLocal portfolioService;
	@Inject CRUDServiceLocal crudService;
	@Inject UserSession userSession;
	 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed(UserRoleConstants.MOBILE_USER)
	public PaginationObject getCustomerList(@DefaultValue("10") @QueryParam("limit") int pageSize, @DefaultValue("1") @QueryParam("offset") int offset,@QueryParam("customerNumber") String customerNumber) throws Exception {
	
		
		 * User preferences should be updated during the user is logged in so keeping in session wont be good
		 * removing the filter in session 
		 * every call will get the customer based on the setting done on the fly.
		 
		//CustomerFilter customerFilter=userSession.getCustomerFilter();
		
		//if (customerFilter == null) {
			
			// Get the mobile user permissions
			// permission will come as the userAccessMap
			CustomerFilter limitFilter = MobileUtil.getCustomerAccessFilter(userSession.getCompanyId(), userSession.getUserId());
			
			// we dont have the uservalue in session get it from db
			UserValue userValue = new UserValue();
			userValue.setIdentifier(userSession.getUserId()); 
			userValue.setCompanyId(userSession.getCompanyId());
			
			userValue =  crudService.findSingleByExample(userValue, null);
			
			// create the customer filter for mobile user
			// when we store the user settings will also get stored
			CustomerFilter customerFilter = new CustomerFilter();
			customerFilter.loadForMobile(userValue.getSettings());

			// based on the permission intersect the filter
			customerFilter = getIntersectedCustomerFilter(customerFilter , limitFilter);

			customerFilter.isMobileUser = true;
			TeamPortfolioSelection teamPortfolioSelection= getTeamPortfolioSelectionForMobile(customerFilter.getIncPortfolio());

			customerFilter.setTeamPortfolioSelection(teamPortfolioSelection);
			//userSession.setCustomerFilter(customerFilter);
        //}
		if(StringUtils.isNotEmpty(customerNumber)){
			String searchKey=customerNumber;
			customerFilter.fromCustomerName = null;
			customerFilter.toCustomerName = null;
			
			//String paddedCustNo = userSession.getCompanyValue().fillCustomerNumber(searchKey, GenericConstants.EQUALS);					
			//customerFilter.setTempSearch(CustomerConstants.CUSTNO, GenericConstants.EQUALS, paddedCustNo);
			String filledFindBySomething = userSession.getCompanyValue().fillCustomerNumber(searchKey,  GenericConstants.CONTAINS);
			customerFilter.quickSearchCustNumber=filledFindBySomething;
			customerFilter.quickSearchModifier=GenericConstants.CONTAINS;
			customerFilter.activeCustomer=true;
		}
        int pageIndex = offset*pageSize;
        //customerFilter.incCustNumbers = new ArrayList();
		PaginationObject page = new PaginationObject();
		List<Map<String,DataMapView>> response=new ArrayList<Map<String,DataMapView>>();
        
		Object objectCount = customerService.findCustomerCountByFilter(userSession.getCompanyId(), customerFilter);
		
		Integer custCount = 0;
		if(objectCount instanceof Integer) {
			custCount = (Integer) objectCount;
		} else {
			custCount = 1;
		}
		
		page.setTotal(custCount);
        if(custCount==0){
        	page.setData(response);
        	return page;	
        }else{
	        //List customerValues = (List) customerService.getCustomerValuesListByFilter(userSession.getCompanyId(), customerFilter, theForm.getSort(), theForm.isDesc(), ++pageIndex, pageSize);
        	
	        List customerValues = (List) customerService.getCustomerValuesListByFilter(userSession.getCompanyId(), customerFilter, "", true, pageIndex, pageSize);
            for(int index=0;index<customerValues.size();index++){
            	debug("sending currency code, perf lang, defMask ["+ userSession.getCompanyValue().getCurrCode() +" , "+userSession.getPrefferedLanguage()+" , "+userSession.getCompanyValue().getDefMask());
            	CustomerValue value=(CustomerValue)customerValues.get(index);
            	if(StringUtils.isNotEmpty(customerNumber)){
            		//fetch the data only when fetching customer details and not the list
            		response.add(conversionService.convert(value, userSession.getAllRoles(),getFormatter(userSession),userSession.getCompanyValue().getCurrCode(),userSession.getPrefferedLanguage(),getPrimayCollector(value.getCustNo()),userSession.getCompanyValue().getDefMask()));
            	}else{
            		response.add(conversionService.convert(value, userSession.getAllRoles(),getFormatter(userSession),userSession.getCompanyValue().getCurrCode(),userSession.getPrefferedLanguage(),null,userSession.getCompanyValue().getDefMask()));
            	}
            }
            page.setData(response);
            page.setColumnConfPreference(conversionService.getColumnConfPreference(userSession.getAllRoles()));
            int currentPageIndex = offset;
            page.setCurrentPageIndex(currentPageIndex);
            page.setTotalPages(custCount, pageSize);
        }	

		
		return page;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed(UserRoleConstants.MOBILE_USER)
	public PaginationObject getAllowedCustomerList(Map<String, String> customerMap) throws Exception {
		
		PaginationObject page = new PaginationObject();
		List<Map<String,DataMapView>> response=new ArrayList<Map<String,DataMapView>>();
		
		if(customerMap.keySet().isEmpty()){
			page.setTotal(0);	        
	        page.setData(response);
	        return page;
		}
		
		//CustomerFilter customerFilter=userSession.getCustomerFilter();
		//if (customerFilter == null) {
			
			// Get the mobile user permissions
			// permission will come as the userAccessMap
			CustomerFilter limitFilter = MobileUtil.getCustomerAccessFilter(userSession.getCompanyId(), userSession.getUserId());
			
			
			// we dont have the uservalue in session get it from db
			UserValue userValue = new UserValue();
			userValue.setIdentifier(userSession.getUserId()); 
			userValue.setCompanyId(userSession.getCompanyId());
			
			userValue =  crudService.findSingleByExample(userValue, null);
			
			// create the customer filter for mobile user
			// when we store the user settings will also get stored
			CustomerFilter customerFilter = new CustomerFilter();
			customerFilter.loadForMobile(userValue.getSettings());

			// based on the permission intersect the filter
			customerFilter = getIntersectedCustomerFilter(customerFilter , limitFilter);

			customerFilter.isMobileUser = true;
			TeamPortfolioSelection teamPortfolioSelection= getTeamPortfolioSelectionForMobile(customerFilter.getIncPortfolio());

			customerFilter.setTeamPortfolioSelection(teamPortfolioSelection);
			userSession.setCustomerFilter(customerFilter);
       // }

		customerFilter.incCustNumbers = new ArrayList();
		
		Set<String> customerNumbers=customerMap.keySet();
		
		for(String number:customerNumbers){
			customerFilter.incCustNumbers.add(number);
		}
		
	    int offset=0;
	    int pageSize=customerNumbers.size()	;
        int pageIndex = offset*pageSize;
        
		
        
		Object objectCount = customerService.findCustomerCountByFilter(userSession.getCompanyId(), customerFilter);
		
		Integer custCount = 0;
		if(objectCount instanceof Integer) {
			custCount = (Integer) objectCount;
		} else {
			custCount = 1;
		}
		
		page.setTotal(custCount);
        if(custCount==0){
        	page.setData(response);
        	return page;	
        }else{
	        //List customerValues = (List) customerService.getCustomerValuesListByFilter(userSession.getCompanyId(), customerFilter, theForm.getSort(), theForm.isDesc(), ++pageIndex, pageSize);
        	debug("sending currency code, perf lang, defMask ["+ userSession.getCompanyValue().getCurrCode() +" , "+userSession.getPrefferedLanguage()+" , "+userSession.getCompanyValue().getDefMask());
	        List customerValues = (List) customerService.getCustomerValuesListByFilter(userSession.getCompanyId(), customerFilter, "", true, pageIndex, pageSize);
            for(int index=0;index<customerValues.size();index++){
            	CustomerValue value=(CustomerValue)customerValues.get(index);
            	response.add(conversionService.convert(value, userSession.getAllRoles(),getFormatter(userSession),userSession.getCompanyValue().getCurrCode(),userSession.getPrefferedLanguage(),getPrimayCollector(value.getCustNo()),userSession.getCompanyValue().getDefMask()));
            }
            page.setData(response);
            page.setColumnConfPreference(conversionService.getColumnConfPreference(userSession.getAllRoles()));
            int currentPageIndex = offset; 
            page.setCurrentPageIndex(currentPageIndex);
            page.setTotalPages(custCount, pageSize);
        }	

		
		return page;
	}
	
	
	private Map<String,Object> getPrimayCollector(String custNo) throws Exception {
		LanguageBundle langBundle = LanguageBundleManager.getBundle( CommonUtil.parseLocale(userSession.getUserLicValue().getLocale()));
		Map<String,Object> map = new HashMap<String,Object>();
		Object fullName = "";
		Object email = "";
		Object emailto = "";
		Object phone = "";
		
		QueryArg args = QueryArg.getInstance();
		args.addArg("custNo", custNo); 
		List userInfo = crudService.executeNamedQuery(userSession.getCompanyId(), PortfolioValue.SEARCHPRIMARYCOLLECTORBYCUSTNO, args);
		if(userInfo.size() > 0){
			UserLicValue o = (UserLicValue)userInfo.get(0);
			  fullName = o.getFullName();
			  email = o.getEmail();
			  emailto = "mailto:" + email;
			  phone = CommonUtil.phoneFormat(o, userSession.getCompanyValue().getDefMask(), null);
		}
		
		if(StringUtils.isBlank(email.toString())){
			email = "";
			emailto = "";
		}
		map.put("fullName", fullName);
		map.put("email", email);
		map.put("emailto", emailto);
		map.put("phone", phone);
		return map;
	}
	
	
	private TeamPortfolioSelection getTeamPortfolioSelectionForMobile(Collection selectedPortfolios) throws Exception {
		getAllowedTeamsAndPortfoliosForMobileCustomer();
		return getTeamPortfolioSelection(selectedPortfolios,UserRoleConstants.MOBILE_USER);
	}
	
	private TeamPortfolioSelection getTeamPortfolioSelection(Collection selectedPortfolios, String mobileUser) {
		if(!CommonUtil.contentExists(selectedPortfolios))
			return null;

		TeamPortfolioSelection teamPortfolioSelection = new TeamPortfolioSelection();

		int size = selectedPortfolios.size();
		if(selectedPortfolios.contains(" ")) {
			size--;
			teamPortfolioSelection.setNullPortfolioCheck(true);
		}

		 //What is this do check
//		int totalPortfolios = (Integer) getSessionObject(SessionConstants.SYSTEM_PORTFOLIO_COUNT);
//		if(totalPortfolios == size) {
//			teamPortfolioSelection.setPortfolioClauseNotRequired(true);
//		}
        
		
		
		List tempTeams = new ArrayList();
		List tempPortfolios = new ArrayList();
		tempPortfolios.addAll(selectedPortfolios);

		tempPortfolios.remove(" ");

		
		SortedMap allowedTeamAndPortfolios = portfolioService.getAllowedTeamsAndPortfoliosForMobileCustomer(userSession.getCompanyId(), userSession.getUserId());
		
		Map teamPortfolioIds = getTeamPortfolioIdsMap(allowedTeamAndPortfolios,mobileUser);
//		if(UserRoleConstants.GP_COLLECTOR.equals(roleId)) {
//			teamPortfolioIds = (Map) getSessionObject(SessionConstants.COLLECTION_ALLOWED_TEAM_PORTFOLIO_IDS);
//		} else if(UserRoleConstants.CM_USER.equals(roleId)) {
//			teamPortfolioIds = (Map) getSessionObject(SessionConstants.CREDIT_ALLOWED_TEAM_PORTFOLIO_IDS);
//		} else if(UserRoleConstants.DRS_USER.equals(roleId)) {
//			teamPortfolioIds = (Map) getSessionObject(SessionConstants.DRS_ALLOWED_TEAM_PORTFOLIO_IDS);
//		}

		if(teamPortfolioIds != null) {
			Iterator itr = teamPortfolioIds.entrySet().iterator();
			while(itr.hasNext()) {
				Map.Entry mapentry = (Map.Entry)itr.next();
				String teamId = (String)mapentry.getKey();
				Collection portfoliosForThisTeam = (Collection)mapentry.getValue();
				if(selectedPortfolios.containsAll(portfoliosForThisTeam) ) {
					tempTeams.add(teamId);
					tempPortfolios.removeAll(portfoliosForThisTeam);
				}
			}
			teamPortfolioSelection.setSelectedTeams(tempTeams);
			teamPortfolioSelection.setPortfoliosWithoutTeam(tempPortfolios);
		}

		return teamPortfolioSelection;
	}
	

	
	private Map getTeamPortfolioIdsMap(Map allowedTeamAndPortfolios, String userRole) {
		Map teamPortfolioIds = new HashMap();
		int portCount = 0;
		Set teamPorfolios = allowedTeamAndPortfolios.entrySet();
		for (Iterator iterator = teamPorfolios.iterator(); iterator.hasNext();) {
			Map.Entry entry = (Entry)iterator.next();
			TeamValue team = (TeamValue) entry.getKey();

			Collection thisTeamsPortfolios = (Collection)entry.getValue();
			Collection portfolioIds = CommonUtil.getIdsFromValues(thisTeamsPortfolios);
			portCount += portfolioIds.size();      

			teamPortfolioIds.put(team.getTeam(), portfolioIds);
		}

		return teamPortfolioIds;
	}
	

	*//**
	 * Returns a SortedMap of the allowed Mobile Customer Portfolios for the requested user
	 * <br>
	 * The map key is a TeamValue and the map value is a Collection of allowed PortfolioValues from the team
	 * 
	 *//*
	private SortedMap getAllowedTeamsAndPortfoliosForMobileCustomer() throws Exception {		
		return portfolioService.getAllowedTeamsAndPortfoliosForMobileCustomer(userSession.getCompanyId(), userSession.getUserId());
	}

	private CustomerFilter getIntersectedCustomerFilter(CustomerFilter formFilter, CustomerFilter limitFilter) throws Exception {
		CustomerFilter customerFilter = new CustomerFilter();
		customerFilter.stages = new boolean[10];
        customerFilter.stages[0] = true;
        customerFilter.stages[1] = true;
        customerFilter.stages[2] = true;
        customerFilter.stages[3] = true;
        customerFilter.stages[4] = true;
        customerFilter.stages[5] = true;
        customerFilter.stages[6] = true;
        customerFilter.stages[7] = true;
        customerFilter.stages[8] = true;
        customerFilter.stages[9] = true;
        
		Collection client;
		Collection admin;
		
		// Compare permissions & user entered values
		// Set the Portfolio
		admin = limitFilter.getIncPortfolio() != null ? limitFilter.getIncPortfolio() : new ArrayList(0);
		client = formFilter.getIncPortfolio();
		customerFilter.setIncPortfolio(CollectionUtils.intersection(admin,client));
		
		// Set the Business unit
		admin = limitFilter.incBusUnit != null ? limitFilter.incBusUnit : new ArrayList(0);
		client = formFilter.incBusUnit;
		customerFilter.incBusUnit = CollectionUtils.intersection(admin,client);

		int result = 0;
		
		// Set the customer name from
		String adminStr = "", clientStr;
		adminStr = CommonUtil.trimToUpper(limitFilter.fromCustomerName);			
		clientStr = CommonUtil.trimToUpper(formFilter.fromCustomerName); 

		if("".equals(adminStr) && "".equals(clientStr)){ // If permissions & preferences are null
			customerFilter.fromCustomerName = null;
		}
		else if("".equals(adminStr) && !"".equals(clientStr)){ // If permissions are null & preferences are not null
			customerFilter.fromCustomerName = clientStr;
			customerFilter.customerNameModifier = GenericConstants.GREATER_OR_EQUAL;
		}
		else if(!"".equals(adminStr) && "".equals(clientStr)){ // If permissions are not null & preferences are null																  
				customerFilter.fromCustomerName = adminStr;
				customerFilter.customerNameModifier = GenericConstants.GREATER_OR_EQUAL;			
		}
		
		if (!"".equals(adminStr) && !"".equals(clientStr)) // Compare the non null values of permissions & preferences
		{
			result = adminStr.compareTo(clientStr);
			if(result >= 0){
				customerFilter.fromCustomerName = adminStr;
			}else{
				customerFilter.fromCustomerName = clientStr;
			}
			customerFilter.customerNameModifier = GenericConstants.GREATER_OR_EQUAL;
		}
		
		// Set the customer name to
		adminStr= CommonUtil.trimToUpper(limitFilter.toCustomerName);
		clientStr = CommonUtil.trimToUpper(formFilter.toCustomerName);

		if("".equals(adminStr) && "".equals(clientStr)){ // If permissions & preferences are is null
			customerFilter.toCustomerName = null;
		}
		else if("".equals(adminStr) && !"".equals(clientStr)){ // If permissions are null & preferences are not null
			customerFilter.toCustomerName = clientStr;				
		}
		else if(!"".equals(adminStr) && "".equals(clientStr)){// If permissions are not null & preferences are null
				customerFilter.toCustomerName = adminStr;			
		}			
		else
		{
			result = adminStr.compareTo(clientStr);
			if(result > 0){
				customerFilter.toCustomerName = clientStr;
			}else{
				customerFilter.toCustomerName = adminStr;
			}
		}
		
		// Set the customer type	
		adminStr = CommonUtil.trimToUpper(limitFilter.customerType);
		clientStr = CommonUtil.trimToUpper(formFilter.customerType);
		client= StringUtil.stringToList(clientStr, UserPreferencesConstants.DELIMITER);
		
		if("".equals(adminStr) && !client.contains("")){
			customerFilter.customerType = clientStr;
		}			
		else{
			customerFilter.customerType = adminStr;
		}
		
		
		// Set the customer territory
		adminStr = CommonUtil.trimToUpper(limitFilter.territory);
		clientStr = CommonUtil.trimToUpper(formFilter.territory);
		client= StringUtil.stringToList(clientStr, UserPreferencesConstants.DELIMITER);
		
		if("".equals(adminStr) && !client.contains("")){
			customerFilter.territory = clientStr;
		}
		else{
			customerFilter.territory = adminStr;
		}
					
		// Set the salesman
		adminStr = CommonUtil.trimToUpper(limitFilter.salesman);
		clientStr = CommonUtil.trimToUpper(formFilter.salesman);	
		client= StringUtil.stringToList(clientStr, UserPreferencesConstants.DELIMITER);
		
		if("".equals(adminStr) && !client.contains("")){
			customerFilter.salesman = clientStr;
		}
		else{
			customerFilter.salesman = adminStr;
		}
	
		// Set the From customer number
		adminStr = CommonUtil.trimToUpper(limitFilter.fromCustomerNumber);			
		clientStr = CommonUtil.trimToUpper(formFilter.fromCustomerNumber);
		
		if("".equals(adminStr) && "".equals(clientStr)){ // If permissions & preferences are null
			customerFilter.fromCustomerNumber = null;
		}
		else if("".equals(adminStr) && !"".equals(clientStr)){// If permissions are null & preferences are not null
			customerFilter.fromCustomerNumber =  clientStr;
			customerFilter.customerNumberModifier = GenericConstants.GREATER_OR_EQUAL;
		}
		else if(!"".equals(adminStr) && "".equals(clientStr)){
			customerFilter.fromCustomerNumber =  adminStr;
			customerFilter.customerNumberModifier = GenericConstants.GREATER_OR_EQUAL;
		}
		else{
			result = clientStr.compareTo(adminStr);
			if(result == 1){
				customerFilter.fromCustomerNumber = adminStr;
			}else{
				customerFilter.fromCustomerNumber = clientStr;
			}
			customerFilter.customerNumberModifier = GenericConstants.GREATER_OR_EQUAL;
		}
		
		// Set the To customer number
		adminStr = CommonUtil.trimToUpper(limitFilter.toCustomerNumber);
		clientStr = CommonUtil.trimToUpper(formFilter.toCustomerNumber) ;		
		
		if("".equals(adminStr) && "".equals(clientStr)){ // If permissions & preferences are null
			customerFilter.toCustomerNumber = null;
		}
		else if("".equals(adminStr) && !"".equals(clientStr)){// If permissions are null & preferences are not null
			customerFilter.toCustomerNumber =  clientStr;
		}
		else if(!"".equals(adminStr) && "".equals(clientStr)){
			customerFilter.toCustomerNumber =  adminStr;
		}
		else{
			result = clientStr.compareTo(adminStr);
			if(result == 1){
				customerFilter.toCustomerNumber = adminStr.toString();
			}else{
				customerFilter.toCustomerNumber = clientStr.toString();
			}
		}
		
		// Set the overdue above value
		Double overdueAbvAdmin = null;
		Double overdueAbvClient = null;
		adminStr = getFormatter(userSession).formatDecimalNumber(limitFilter.overAbove);
		if(StringUtils.isNotBlank(adminStr)){
			overdueAbvAdmin = new Double(getFormatter(userSession).parseDecimalNumber(adminStr));
		}
		
		clientStr = getFormatter(userSession).formatDecimalNumber(formFilter.overAbove);			
		if(StringUtils.isNotBlank(clientStr)){
			overdueAbvClient = new Double(getFormatter(userSession).parseDecimalNumber(clientStr));
		}

		if(overdueAbvClient != null && overdueAbvAdmin != null){ 
			result = overdueAbvClient.compareTo(overdueAbvAdmin);
			if(result == 1){
				customerFilter.overAbove = overdueAbvClient.doubleValue();
			}else{
				customerFilter.overAbove = overdueAbvAdmin.doubleValue();
			}
		}
		else if(overdueAbvClient == null && overdueAbvAdmin != null){
			customerFilter.overAbove = overdueAbvAdmin.doubleValue();
		}
		else if(overdueAbvClient != null && overdueAbvAdmin == null){
			customerFilter.overAbove = overdueAbvClient.doubleValue();
		}
		
		// Set the overdue below value
		Double overdueBelAdmin = null;
		Double overdueBelClient = null;
		
		adminStr = getFormatter(userSession).formatDecimalNumber(limitFilter.overBelow);
		if(StringUtils.isNotBlank(adminStr)){
			overdueBelAdmin = new Double(getFormatter(userSession).parseDecimalNumber(adminStr));
		}
		
		clientStr = getFormatter(userSession).formatDecimalNumber(formFilter.overBelow);			
		if(StringUtils.isNotBlank(clientStr)){
			overdueBelClient = new Double(getFormatter(userSession).parseDecimalNumber(clientStr));
		}
		
		if(overdueBelClient != null && overdueBelAdmin != null){ 
			result = overdueBelClient.compareTo(overdueBelAdmin);
			if(result == -1){
				customerFilter.overBelow = overdueBelClient.doubleValue();
			}else{
				customerFilter.overBelow = overdueBelAdmin.doubleValue();
			}
		}
		else if(overdueBelClient == null && overdueBelAdmin != null){
			customerFilter.overBelow = overdueBelAdmin.doubleValue();
		}
		else if(overdueBelClient != null && overdueBelAdmin == null){
			customerFilter.overBelow = overdueBelClient.doubleValue();
		}
		
		return customerFilter;		
	}

	


///============================================================================================================================

*/}
