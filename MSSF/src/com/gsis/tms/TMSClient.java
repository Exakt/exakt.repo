package com.gsis.tms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.gsis.tms.TMSStub.DataReturn;
import com.gsis.tms.TMSStub.RetrieveTMSTransactionsResponse;
import com.gsis.tms.TMSStub.RetrieveTMSTransactionsResponseE;
import com.gsis.tms.TMSStub.TransactionDetails;

public class TMSClient {
	
	public static void main(String args[]){
		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			TMSStub stub = new TMSStub();
			TMSStub.RetrieveTMSTransactions retrieveTMS = new TMSStub.RetrieveTMSTransactions();
			retrieveTMS.setGsisidnumber("BP2000966177");
			retrieveTMS.setSyscode("1055");
			
			TMSStub.RetrieveTMSTransactionsE retrieveTmsE= new TMSStub.RetrieveTMSTransactionsE();
			
			retrieveTmsE.setRetrieveTMSTransactions(retrieveTMS);
			
			RetrieveTMSTransactionsResponseE responseE = stub.retrieveTMSTransactions(retrieveTmsE);
			RetrieveTMSTransactionsResponse response= responseE.getRetrieveTMSTransactionsResponse();
			
			DataReturn dataReturn = response.get_return();
			
			int flag = Integer.parseInt(dataReturn.getReturnFlag());
			
			if(flag == 0){
				
				TransactionDetails[] transList = dataReturn.getTransactionDetails();
				
				for(int x = 0; x < transList.length; x++){
					System.out.println(transList[x].getTransactionRefNo());
					System.out.println(transList[x].getTransactionDescription());
					System.out.println(transList[x].getDateFiled());
					System.out.println(transList[x].getStatus());
					System.out.println("========================================");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
