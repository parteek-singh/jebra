package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class testclass {/*
	

	///===========================================


		
		@InjectMocks
		@Spy
		TransactionResource mockTransactionResource;
		
		@Mock
		TransactionService tranServiceMock;
		
		@Mock
		CustomerService customerServiceMock;
		
		@Mock
		StrategyService strategyServiceMock;
		
		@Mock
		CompanyService companyServiceMock;
		
		@Mock
		Formatter formatter;
		
		@Mock
		PayableAdviceService payableAdviceServiceMock;
		
		@Mock
		FollowUpService followUpServiceMock;
		
		private Integer companyId = 1;
		private String custNo = "12345";
		
		
		*//**
		 * Test for method : combine()
		 * 
		 * @throws Exception
		 *//*
		@Test
		public void testCombineSuccess() {
			//int updateInProgress =0;
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			String familyView ="N";
		
			try {
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				
				QuickPromiseView quickPromiseView =new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView(familyView);
				
				doReturn(0).when(mockTransactionResource).checkUpdateInProgress();
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				doReturn(companyId).when(mockTransactionResource).getCompanyId();
				doReturn(getUserId()).when(mockTransactionResource).getUserId();
				
				doReturn(getWorkingCustomer()).when(mockTransactionResource)
				.getCurrWorkingCustomerValue(any(String.class));
				
				doReturn(false).when(mockTransactionResource)
				.isCallInProgress();
				
				doReturn(tranPks).when(mockTransactionResource)
				.setSelectedTranIds(any(int[].class),any(Integer.class));
				 
				
				
				Mockito.doNothing().when(mockTransactionResource)
				.setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
				
				
				Response response = mockTransactionResource
						.combine(quickPromiseView);
				assertNotNull(response);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@SuppressWarnings("unchecked")
		@Test
		public void testCombineThrowException() {
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			
			try {
				List<ColumnMetaValue> columnValueList = new ArrayList<ColumnMetaValue>();
				
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				Collection<BasePk> descendentPks = new ArrayList<BasePk>();
				descendentPks.add(new BasePk());
				QuickPromiseView quickPromiseView =new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView("N");
				
				doReturn(0).when(mockTransactionResource).checkUpdateInProgress();
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				doReturn(companyId).when(mockTransactionResource).getCompanyId();
				doReturn(getUserId()).when(mockTransactionResource).getUserId();
				
				doReturn(getWorkingCustomer()).when(mockTransactionResource)
				.getCurrWorkingCustomerValue(any(String.class));
				
				doReturn(false).when(mockTransactionResource)
				.isCallInProgress();
				
				doReturn(tranPks).when(mockTransactionResource)
				.setSelectedTranIds(any(int[].class),any(Integer.class));
				
			 
				doReturn(descendentPks).when(mockTransactionResource)
				.getDescendents();
				
				
				doReturn(columnValueList).when(mockTransactionResource).findByFilter(eq(CustomerValue.class), any(Collection.class), any(ResultConstraints.class),  any(Integer.class));
				
				Mockito.doNothing().when(mockTransactionResource)
				.setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
				//tranPks, companyId, getUserId()
				doThrow(new GPUpdateEntityException("Error"))
				.when(tranServiceMock).combine(any(Collection.class),any(Integer.class),any(Integer.class));
				
				Response response = mockTransactionResource
						.combine(quickPromiseView);
				assertNotNull(response);
				
				
			}catch (GPUpdateEntityException e) {

				Assert.assertEquals("Error", e.getMessage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void testCombineWithFamilyView() {
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			String familyView ="Y";
		
			try {
				List<ColumnMetaValue> columnValueList = new ArrayList<ColumnMetaValue>();
				
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				Collection<BasePk> descendentPks = new ArrayList<BasePk>();
				descendentPks.add(new BasePk());
				QuickPromiseView quickPromiseView =new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView(familyView);
				
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				doReturn(companyId).when(mockTransactionResource).getCompanyId();
				doReturn(getUserId()).when(mockTransactionResource).getUserId();
				
				doReturn(getWorkingCustomer()).when(mockTransactionResource)
				.getCurrWorkingCustomerValue(any(String.class));
				
				doReturn(false).when(mockTransactionResource)
				.isCallInProgress();
				
				doReturn(tranPks).when(mockTransactionResource)
				.setSelectedTranIds(any(int[].class),any(Integer.class));
				
			 
				doReturn(descendentPks).when(mockTransactionResource)
				.getDescendents();
				
				
				doReturn(columnValueList).when(mockTransactionResource).findByFilter(eq(CustomerValue.class), any(Collection.class), any(ResultConstraints.class),  any(Integer.class));
				
				Mockito.doNothing().when(mockTransactionResource)
				.setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
				
				Response response = mockTransactionResource
						.combine(quickPromiseView);
				assertNotNull(response);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void testGetPaymentPlansDetails() {
			
			PaymentPlanView paymentPlanView = new PaymentPlanView();
			paymentPlanView.setStartDateCB("7/1/17");
			paymentPlanView.setStartDateTB("6/1/17");
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(strategyServiceMock).when(mockTransactionResource).getService(StrategyService.class);		
			doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
			
			StrategyValue stratValue = new StrategyValue();
			stratValue.setQpdays(15);
			
			doReturn(stratValue).when(strategyServiceMock).getCustomerStrategyValue(any(Integer.class), any(CustomerValue.class));
			doReturn(companyServiceMock).when(mockTransactionResource).getService(CompanyService.class);
			doReturn(stratValue.getQpdays().intValue()).when(mockTransactionResource).getIntFromObj(stratValue.getQpdays());
			
			Calendar beginDate = Calendar.getInstance();
			beginDate.set(2017, 06, 01);
			doReturn(beginDate).when(companyServiceMock).rollCalendarAhead(any(CompanyValue.class), any(Calendar.class), any(Integer.class));
			doReturn(getMockedFormatter()).when(mockTransactionResource).getFormatter();		
			
			Response response = mockTransactionResource.getPaymentPlansDetails(custNo);
			assertNotNull(response);
			
			Assert.assertEquals(paymentPlanView.getStartDateCB(), ((PaymentPlanView)response.getEntity()).getStartDateCB());		
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void testGetPaymentPlansDetailsHandleRuntimeExpection() {
			String runtimeError = "GP.CLIENT.TRANSACTION.UPDATE";
			String[] messageKey = {runtimeError};
			Response errorResponse = Response.status(Status.BAD_REQUEST).entity(runtimeError).build();
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(strategyServiceMock).when(mockTransactionResource).getService(StrategyService.class);		
			doReturn(errorResponse).when(mockTransactionResource).genrateException(messageKey);

			Response response = mockTransactionResource.getPaymentPlansDetails(custNo);
			assertNotNull(response);
			Assert.assertEquals(response.getEntity(), runtimeError);
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void testValidatePaymentPlanDetailsForCollectionTransaction() throws Exception{
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			
			PaymentPlanView paymentPlanView = new PaymentPlanView();
			paymentPlanView.setTranId(tranIds);
			
			// Single collection transaction
			TransactionValue transactionValue_1 = new TransactionValue();
			transactionValue_1.setAmount(100d);
			
			Collection<TransactionValue> tranList = new ArrayList<TransactionValue>();
			tranList.add(transactionValue_1);
			
			ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
			doReturn(tranList).when(mockTransactionResource).findByFilter(eq(TransactionValue.class), any(Collection.class), any(ResultConstraints.class), any(Integer.class));
			
			Response response = mockTransactionResource.validatePaymentPlanDetails(paymentPlanView);
			assertNotNull(response);		
		}
		
		@SuppressWarnings({"unchecked", "deprecation"})
		@Test
		public void testValidatePaymentPlanDetailsForCreditTransaction() throws Exception{
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			
			String runtimeError = "Payment Plans can not be made on credit transactions!";		
			Response errorResponse = Response.status(Status.BAD_REQUEST).entity(runtimeError).build();
			
			PaymentPlanView paymentPlanView = new PaymentPlanView();
			paymentPlanView.setTranId(tranIds);
			
			// Single Credit transaction
			TransactionValue transactionValue_1 = new TransactionValue();
			transactionValue_1.setAmount(-100d);
			
			Collection<TransactionValue> tranList = new ArrayList<TransactionValue>();
			tranList.add(transactionValue_1);
			
			ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
			doReturn(tranList).when(mockTransactionResource).findByFilter(eq(TransactionValue.class), any(Collection.class), any(ResultConstraints.class), any(Integer.class));
			doReturn(errorResponse).when(mockTransactionResource).genrateException(any(String[].class));
			
			Response response = mockTransactionResource.validatePaymentPlanDetails(paymentPlanView);
			
			assertNotNull(response);
			Assert.assertEquals(response.getEntity(), runtimeError);						
		}
		
		@SuppressWarnings({"unchecked", "deprecation"})
		@Test
		public void testValidatePaymentPlanDetailsForCreditAndCollectionTransaction() throws Exception{
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			
			PaymentPlanView paymentPlanView = new PaymentPlanView();
			paymentPlanView.setTranId(tranIds);
			
			// Collection transaction
			TransactionValue transactionValue_1 = new TransactionValue();
			transactionValue_1.setAmount(-100d);
			
			// Credit transaction
			TransactionValue transactionValue_2 = new TransactionValue();
			transactionValue_2.setAmount(100d);
			
			Collection<TransactionValue> tranList = new ArrayList<TransactionValue>();
			tranList.add(transactionValue_1);
			tranList.add(transactionValue_2);
			
			ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
			doReturn(tranList).when(mockTransactionResource).findByFilter(eq(TransactionValue.class), any(Collection.class), any(ResultConstraints.class), any(Integer.class));
			
			Response response = mockTransactionResource.validatePaymentPlanDetails(paymentPlanView);
			assertNotNull(response);
		}
		
		
		@SuppressWarnings("deprecation")
		@Test
		public void testdoPaymentPlanCustomerBased() throws Exception{
			
			PaymentPlanView paymentPlanView = getPaymentPlanObject("C");
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
			doReturn(getMockedFormatter()).when(mockTransactionResource).getFormatter();
			doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
					
			HolidayValue holidayValue = new HolidayValue(new Date());
			Collection<HolidayValue> holidays = new ArrayList<HolidayValue>();
			holidays.add(holidayValue);
			doReturn(holidays).when(mockTransactionResource).findAll(eq(HolidayValue.class), any(ResultConstraints.class), any(Integer.class));
			doReturn(getLanguageBundle()).when(mockTransactionResource).getLanguageBundle();
			doReturn(getUserLicValue()).when(mockTransactionResource).getUserLicValue();
			
			Mockito.doNothing().when(tranServiceMock).createPayPlanByCustomer(any(CompanyValue.class), any(Double.class), any(Date.class), any(Integer.class), any(Integer.class), any(Boolean.class), 
							any(CustomerValue.class), any(Boolean.class), any(String.class), any(UserLicValue.class));
			
			doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
			doReturn(Boolean.FALSE).when(customerServiceMock).isMajorAccount(any(Integer.class), any(CustomerValue.class));
			
			Collection<BasePk> descendentPks = new ArrayList<BasePk>();
			descendentPks.add(new BasePk());
			doReturn(descendentPks).when(mockTransactionResource).getDescendents();
			Mockito.doNothing().when(mockTransactionResource).setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
			
			Response response = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(response);
			Assert.assertEquals((PaymentPlanView)response.getEntity(), paymentPlanView);
			
			// JUnit Test Case : For Invalid Amount
			paymentPlanView.setAmtPayments("(1500)");
			
			String invalidAmount = "0xE444";
			String[] messageKey = {invalidAmount};
			Response errorResponse = Response.status(Status.BAD_REQUEST).entity(invalidAmount).build();
			doReturn(errorResponse).when(mockTransactionResource).genrateException(messageKey);
			
			Response responseInvalidAmount = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(responseInvalidAmount);
			Assert.assertEquals(responseInvalidAmount.getEntity(), invalidAmount);
			
			// JUnit Test Case : For Start Date before today
			paymentPlanView.setAmtPayments("1500");
			paymentPlanView.setStartDate("06/01/17");
			
			String pastDate = "0xFDA7";
			String[] pastDateMsgKey = {pastDate};
			Response dateErrorResponse = Response.status(Status.BAD_REQUEST).entity(pastDate).build();
			doReturn(dateErrorResponse).when(mockTransactionResource).genrateException(pastDateMsgKey);
			
			Response responseInvalidDate = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(responseInvalidDate);
			Assert.assertEquals(responseInvalidDate.getEntity(), pastDate);		
		}
		
		@SuppressWarnings({"deprecation", "unchecked"})
		@Test
		public void testdoPaymentPlanTransactionBased() throws Exception{
			
			PaymentPlanView paymentPlanView = getPaymentPlanObject("T");
			
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
			doReturn(getMockedFormatter()).when(mockTransactionResource).getFormatter();
			doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
					
			HolidayValue holidayValue = new HolidayValue(new Date());
			Collection<HolidayValue> holidays = new ArrayList<HolidayValue>();
			holidays.add(holidayValue);
			doReturn(holidays).when(mockTransactionResource).findAll(eq(HolidayValue.class), any(ResultConstraints.class), any(Integer.class));
			
			List<TransactionValue> transactionList = new ArrayList<TransactionValue>();
			doReturn(transactionList).when(mockTransactionResource).findByFilter(eq(TransactionValue.class), any(Collection.class), any(Integer.class));
			
			doReturn(getLanguageBundle()).when(mockTransactionResource).getLanguageBundle();
			doReturn(getUserLicValue()).when(mockTransactionResource).getUserLicValue();
			doReturn("GET").when(mockTransactionResource).getUserDisplayName();
			doReturn(getUserId()).when(mockTransactionResource).getUserId();
			Mockito.doNothing().when(tranServiceMock).createPaymentPlan(any(Collection.class), any(Boolean.class), any(Date.class), any(String.class), any(Integer.class), any(Integer.class), any(Double.class), 
							any(String.class), any(String.class), any(Integer.class), any(Integer.class));
			
			doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
			doReturn(Boolean.FALSE).when(customerServiceMock).isMajorAccount(any(Integer.class), any(CustomerValue.class));
			
			Collection<BasePk> descendentPks = new ArrayList<BasePk>();
			descendentPks.add(new BasePk());
			doReturn(descendentPks).when(mockTransactionResource).getDescendents();
			Mockito.doNothing().when(mockTransactionResource).setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
			
			
			Response response = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(response);
			Assert.assertEquals((PaymentPlanView) response.getEntity(), paymentPlanView);
			
			// JUnit Test Case : For Invalid Amount
			paymentPlanView.setAmtPayments("0");
			paymentPlanView.setPaymentMethod("basePay");
			
			String invalidAmount = "0xE444";
			String[] messageKey = {invalidAmount};
			Response errorResponse = Response.status(Status.BAD_REQUEST).entity(invalidAmount).build();
			doReturn(errorResponse).when(mockTransactionResource).genrateException(messageKey);
			
			Response responseInvalidAmount = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(responseInvalidAmount);
			Assert.assertEquals(responseInvalidAmount.getEntity(), invalidAmount);
			
			// JUnit Test Case : For Start Date before today
			paymentPlanView.setAmtPayments("1500");		
			paymentPlanView.setStartDate("06/01/17");
			
			String pastDate = "0xFDA7";
			String[] pastDateMsgKey = {pastDate};
			Response dateErrorResponse = Response.status(Status.BAD_REQUEST).entity(pastDate).build();
			doReturn(dateErrorResponse).when(mockTransactionResource).genrateException(pastDateMsgKey);
			
			Response responseInvalidDate = mockTransactionResource.doPaymentPlan(paymentPlanView);
			assertNotNull(responseInvalidDate);
			Assert.assertEquals(responseInvalidDate.getEntity(), pastDate);		
		}
		
		private PaymentPlanView getPaymentPlanObject(String paymentType){
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			
			PaymentPlanView paymentPlanView = new PaymentPlanView();
			paymentPlanView.setCustNo(custNo);
			paymentPlanView.setTranId(tranIds);
			paymentPlanView.setType(paymentType);
			paymentPlanView.setAmtPayments("1500");
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			Date date = new Date();
			paymentPlanView.setStartDate(dateFormat.format(date));
			
			paymentPlanView.setNumPayments("2");
			paymentPlanView.setDaysBetween("2");
			paymentPlanView.setPaymentMethod("numberPay");
			paymentPlanView.setFamilyView("Y");
			paymentPlanView.setHasChild(false);
			return paymentPlanView;
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void testGetAdviceUploadDefinedFiles() {
			DefFileValue defFileValue = new DefFileValue();
			defFileValue.setAppType(GenericConstants.COLLECTION_FLAG);
			
			List<DefFileValue> defFiles = new ArrayList<DefFileValue>();
			try {
				doReturn(defFiles).when(mockTransactionResource).findByExample(defFileValue);
			}
			catch (Exception e) {}
			
			// Scenario 1 : When no custom files are defined
			Response adviceDefFilesResponse = mockTransactionResource.getAdviceUploadDefinedFiles();
			AdviceUploadView adviceUploadView = (AdviceUploadView)adviceDefFilesResponse.getEntity();
			Assert.assertEquals(adviceUploadView.getFileType(), 1);
			Assert.assertEquals(adviceUploadView.getDefFiles(), defFiles);
			Assert.assertTrue(adviceUploadView.isDoConfirm());
			
			
			// Scenario 2 : When custom files are defined
			DefFileValue def1 = new DefFileValue();
			def1.setDefFileId(1);
			def1.setDescription("Style 1");
			
			DefFileValue def2 = new DefFileValue();
			def2.setDefFileId(2);
			def2.setDescription("Style 2");
			
			defFiles.add(def1);
			defFiles.add(def2);
			
			try {
				doReturn(defFiles).when(mockTransactionResource).findByExample(defFileValue);
			}
			catch (Exception e) {}
			
			Response adviceDefFilesResponse1 = mockTransactionResource.getAdviceUploadDefinedFiles();
			AdviceUploadView adviceUploadView1 = (AdviceUploadView)adviceDefFilesResponse1.getEntity();
			Assert.assertEquals(adviceUploadView1.getDefFiles().get(0), def1);
			Assert.assertEquals(adviceUploadView1.getDefFiles().get(1), def2);
			Assert.assertTrue(adviceUploadView1.isDoConfirm());
		}
		
		@SuppressWarnings({"unchecked", "deprecation"})
		@Test
		public void testSaveAdviceUpload() throws Exception {

			FileInfo fileInfo = this.getFileInfoTestObj();

			AdviceUploadView adviceUploadView = new AdviceUploadView();
			adviceUploadView.setFileInfo(fileInfo);
			adviceUploadView.setCustNo(custNo);
			adviceUploadView.setFamilyView("Y");
			adviceUploadView.setDefFileId(1);
			adviceUploadView.setDoConfirm(true);
			
			doReturn(10).when(mockTransactionResource).checkUpdateInProgress();

			Map<String, byte[]> adviceUploadDataMap = new LinkedHashMap<String, byte[]>(2);
			byte[] fileData = "This is my advice file upload file data".getBytes();
			byte[] zipData = "This is my advice file upload zip data".getBytes();
			adviceUploadDataMap.put("fileData", fileData);
			adviceUploadDataMap.put("zipData", zipData);

			doReturn(adviceUploadDataMap).when(mockTransactionResource).getAdviceUploadFileDetails(fileInfo);
			doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
			
			Collection<BasePk> descendents = new ArrayList<BasePk>();
			descendents.add(new BasePk());
			doReturn(descendents).when(mockTransactionResource).getDescendents();
			
			doReturn(payableAdviceServiceMock).when(mockTransactionResource).getService(eq(PayableAdviceService.class));
			doReturn(getLanguageBundle()).when(mockTransactionResource).getLanguageBundle();
			
			Map<String, TransactionControlValue> transactionCtrlMap = new HashMap<String, TransactionControlValue>();
			doReturn(transactionCtrlMap).when(mockTransactionResource).getTransactionControlMap();
			
			doReturn("GET").when(mockTransactionResource).getUserDisplayName();
			doReturn(getMockedFormatter()).when(mockTransactionResource).getFormatter();
			doReturn(getUserId()).when(mockTransactionResource).getUserId();
			doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
			
			
			PayableUploadValue logUploadValue = new PayableUploadValue();
			doReturn(logUploadValue).when(payableAdviceServiceMock).processPayableAdviceData(any(CustomerValue.class), any(Integer.class), any(byte[].class), any(LanguageBundle.class), 
							any(HashMap.class), any(String.class), any(Formatter.class), any(Boolean.class), any(Integer.class), any(Integer.class), any(List.class), any(byte[].class));
			
			String statisticMsg = "File Uploaded Successfully"; 
			doReturn(statisticMsg).when(mockTransactionResource).getAdviceUploadStatisticsMessage(any(PayableUploadValue.class));
			Mockito.doNothing().when(mockTransactionResource).updateDrivingTransaction(any(CustomerValue.class), any(Collection.class), any(String.class));

			Response adviceSaveResponse = mockTransactionResource.saveAdviceUpload(adviceUploadView);
			Assert.assertNotNull(adviceSaveResponse);
			Assert.assertEquals(((AdviceUploadView)adviceSaveResponse.getEntity()).getStatisticMsg(), statisticMsg);

		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void testGetAdviceUploadStatisticsMessage() {
			PayableUploadValue logUploadValue = new PayableUploadValue();
			logUploadValue.setTotalRows(3);
			doReturn(getLanguageBundle()).when(mockTransactionResource).getLanguageBundle();
			
			// Scenario 1: When no exception row in PayableUploadValue
			String statisticMsg = mockTransactionResource.getAdviceUploadStatisticsMessage(logUploadValue);
			Assert.assertEquals(statisticMsg, "Records Processed: 3 --- No exceptions. Process was successfully completed.");
			
			// Scenario 2: PayableUploadValue has exception row
			logUploadValue.setExceptionRows(2);
			String statisticMsg1 = mockTransactionResource.getAdviceUploadStatisticsMessage(logUploadValue);
			Assert.assertEquals(statisticMsg1, "Records Processed: 3 --- Successfully Updated: 1 --- Batch Exceptions: 2");
			
			// Scenario 3 : Create new problem
			logUploadValue.setNewProbIds("12345");
			String statisticMsg2 = mockTransactionResource.getAdviceUploadStatisticsMessage(logUploadValue);
			Assert.assertEquals(statisticMsg2, "Records Processed: 3 --- Successfully Updated: 1 --- Batch Exceptions: 2 --- Create New Problem: 12345");
		}
		
		@Test
		public void testUpdateDrivingTransaction() {
			String showFamily = "N";

			// When Major Customer and Call is in progress
			doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
			doReturn(companyId).when(mockTransactionResource).getCompanyId();
			doReturn(true).when(customerServiceMock).isMajorAccount(any(Integer.class), any(CustomerValue.class));
			doReturn(true).when(mockTransactionResource).isCallInProgress();

			CustomerValue customerValue = new CustomerValue(custNo);
			Collection<BasePk> descendents = new ArrayList<BasePk>();
			descendents.add(new BasePk());

			mockTransactionResource.updateDrivingTransaction(customerValue, descendents, showFamily);

			// When Major Customer but no Call in Progress
			doReturn(false).when(mockTransactionResource).isCallInProgress();
			mockTransactionResource.updateDrivingTransaction(customerValue, descendents, showFamily);

			// When No Major Customer but Call in Progress
			doReturn(false).when(customerServiceMock).isMajorAccount(any(Integer.class), any(CustomerValue.class));
			doReturn(true).when(mockTransactionResource).isCallInProgress();
			mockTransactionResource.updateDrivingTransaction(customerValue, descendents, showFamily);

			// When No Major Customer and No Call in Progress, but no family
			doReturn(false).when(customerServiceMock).isMajorAccount(any(Integer.class), any(CustomerValue.class));
			doReturn(false).when(mockTransactionResource).isCallInProgress();
			doReturn(companyId).when(mockTransactionResource).getCompanyId();
			doReturn(getUserId()).when(mockTransactionResource).getUserId();
			Mockito.doNothing().when(mockTransactionResource).setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
			mockTransactionResource.updateDrivingTransaction(customerValue, descendents, showFamily);

			// When No Major Customer and No Call in Progress, but family
			showFamily = "Y";
			List<CustomerValue> customerValues = new ArrayList<CustomerValue>();
			customerValues.add(customerValue);
			doReturn(customerValues).when(customerServiceMock).getLargeCustomerValueList(any(List.class), any(Integer.class));
			mockTransactionResource.updateDrivingTransaction(customerValue, descendents, showFamily);		
		}
			
		private FileInfo getFileInfoTestObj() {

			FileInfo fileInfo = new FileInfo();
			fileInfo.setContentType("contentType");
			fileInfo.setDescription("File Description");
			fileInfo.setMessage("File Message");
			fileInfo.setName("Advice_File.xml");
			fileInfo.setSize(200l);
			fileInfo.setStatus(true);
			fileInfo.setUuid("ABC-DEF_GHI");

			return fileInfo;
		}
		
		@Test 
		public void testQuickPromiseThrowsException() throws Exception{
			try
			{
				
				
				String updateInProgressErrorMsg = "0xFA48";
				String[] messageKey = {updateInProgressErrorMsg};
				
				doThrow(new Exception("Error")).when(mockTransactionResource).quickPromise(quickPromiseView);
				
				Response response = mockTransactionResource.quicSERVERkPromise(quickPromiseView);
				assertNotNull(response);	
					
				Response errorResponse = Response.status(Status.BAD_REQUEST).entity(updateInProgressErrorMsg).build();
				doReturn(errorResponse).when(mockTransactionResource).genrateException(messageKey);
				
				QuickPromiseView quickPromiseView = new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				
				int[] tranIds = new int[2];
				tranIds[0] = 100;
				tranIds[1] = 101;
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView("N");
				
				doReturn(Boolean.FALSE).when(mockTransactionResource).isUserInRole(UserRoleConstants.DRS_USER);
				doReturn(getMockedFormatter()).when(mockTransactionResource).getFormatter();
				doReturn(getCompanyValueForTest()).when(mockTransactionResource).getCompanyValue();
				doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
				doReturn(strategyServiceMock).when(mockTransactionResource).getService(StrategyService.class);		
				doReturn(followUpServiceMock).when(mockTransactionResource).getService(StrategyService.class);		
				doReturn(companyServiceMock).when(mockTransactionResource).getService(CompanyService.class);
				
				@SuppressWarnings("unchecked")
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
				
				//doReturn(isCallInProgress()).when(mockTransactionResource).isCallInProgress(any(String.class));
				
				StrategyValue stratValue = new StrategyValue();
				stratValue.setQpdays(15);
				doReturn(new RuntimeException("Strategy Exception")).when(strategyServiceMock).getCustomerStrategyValue(any(Integer.class), any(CustomerValue.class));
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				Response quickPromiseResponse = mockTransactionResource.quickPromise(quickPromiseView);
			} 
			catch (Exception e) 
			{		
				e.printStackTrace();
			} 	
		}
			
		@Test
		public void testQuickPromiseSuccess() {
			int[] tranIds = new int[2];
			tranIds[0] = 100;
			tranIds[1] = 101;
			String familyView ="N";
		
			try 
			{
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				
				QuickPromiseView quickPromiseView =new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView(familyView);
				
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				doReturn(companyId).when(mockTransactionResource).getCompanyId();
				doReturn(getUserId()).when(mockTransactionResource).getUserId();
				
				doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
				
				doReturn(false).when(mockTransactionResource).isCallInProgress();
				
				doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
				 
				Mockito.doNothing().when(mockTransactionResource).setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
							
				Response response = mockTransactionResource.quickPromise(quickPromiseView);
				assertNotNull(response);
						
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void testQuickPromiseWithFamilyView() {
			
			int[] tranIds = new int[2];
			tranIds[0] = 103;
			tranIds[1] = 104;
			String familyView ="Y";
		
			try 
			{
				List<ColumnMetaValue> columnValueList = new ArrayList<ColumnMetaValue>();
				
				ArrayList<TransactionPk> tranPks = getTranPkList(companyId,tranIds);
				Collection<BasePk> descendentPks = new ArrayList<BasePk>();
				descendentPks.add(new BasePk());
				QuickPromiseView quickPromiseView =new QuickPromiseView();
				quickPromiseView.setCustNo(custNo);
				quickPromiseView.setTranId(tranIds);
				quickPromiseView.setFamilyView(familyView);
				
				doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
				doReturn(customerServiceMock).when(mockTransactionResource).getService(CustomerService.class);
				
				doReturn(companyId).when(mockTransactionResource).getCompanyId();
				doReturn(getUserId()).when(mockTransactionResource).getUserId();
				
				doReturn(getWorkingCustomer()).when(mockTransactionResource).getCurrWorkingCustomerValue(any(String.class));
				
				doReturn(false).when(mockTransactionResource).isCallInProgress();
				
				doReturn(tranPks).when(mockTransactionResource).setSelectedTranIds(any(int[].class),any(Integer.class));
					 
				doReturn(descendentPks).when(mockTransactionResource).getDescendents();
					
				doReturn(columnValueList).when(mockTransactionResource).findByFilter(eq(CustomerValue.class), any(Collection.class), any(ResultConstraints.class),  any(Integer.class));
				
				Mockito.doNothing().when(mockTransactionResource).setDrivingTransactions(any(Integer.class),any(Integer.class),any(Collection.class),any(Boolean.class),any(CustomerValue.class));
				
				Response response = mockTransactionResource.quickPromise(quickPromiseView);
				assertNotNull(response);		
				 
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Test
		public void testQuickPromiseUpdateTransactionsSuccess() 
		{
			doReturn(tranServiceMock).when(mockTransactionResource).getService(TransactionService.class);
			
			try
			{	
				Mockito.doNothing().when(tranServiceMock).updateTransactions(null, custNo, custNo, custNo, custNo, custNo, custNo, custNo, custNo, custNo, custNo, custNo, false, custNo, null, companyId, companyId, custNo, null, formatter, null, custNo, false, companyId, null);
			}
			catch (GPUpdateEntityException ge) {
				ge.printStackTrace();                                                                                                                                                                                                                                                                                                                                                                                                                             
			}
			catch (InvalidAmountException ive) {
				ive.printStackTrace();
			}
			
		}

*/}
