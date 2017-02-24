package com.testcase.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;


import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.DetokenizationDictionary;
import opennlp.tools.tokenize.DictionaryDetokenizer;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.tokenize.DetokenizationDictionary.Operation;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class testNlp {

	String entity;
	public static ArrayList<String> TestSet = new ArrayList<String>();
	public static ArrayList<String> ReqSet = new ArrayList<String>();
	String path="D:\\Test case pro\\DesignPlus v1.4\\src\\";
	
	
	
	public ArrayList<String> GenerateTestCase(String paragraph) throws IOException{
		ArrayList<String> TestCaseList = new ArrayList<String>();
		testNlp.TestSet=new ArrayList<String>(); // Added this new line
		new testNlp().RuleMap(paragraph);
		TestCaseList= testNlp.TestSet;
		
		return TestCaseList;
		
	}
	
public void RuleMap(String paragraph) throws IOException {
	
	String whitespaceTokenizerLine[];
	String Input = paragraph;
	
	
	String[][] Grammar = {
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","PRP","_MD","_VB","_VB"}, //student should be able to login and he should be able to register.
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","PRP","_MD","_VB"},  //student should be able to login and he can register.
						  {"_NN/_NNP/_NNS","_MD","_VB","PRP","_MD","_VB"},//student can login and he can register.
						  {"_NN/_NNP/_NNS","_MD","_VB","PRP","_MD","_VB","_VB"}, //student can login and he should be able register.
						  
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_NN","_CC","_VB/_NN"}, //and Student would be/will be/should be/must be login to the system. j=0 
						  {"_NN/_NNP/NNS","_MD","_VB","_NN","_CC","_VB/_NN"}, //Student can login to the system and register 1
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_CC","_VB/_NN"}, //Student would be/will be/should be/must be login and register.	2
						  {"_NN/_NNP/_NNS","_MD","_VB","_CC","_VB/_NN"}, //Student can login and register
						  {"_NN/_NNP/NNS","_NN/_NNS","_IN","_NN/_NNS","_CC","_VB/_NN"}, // Manager register/s for courses and login.
						  {"_NN/_NNP/_NNS","_VBD/_NNP/_VBZ","_VB","_NN","_CC","_VB/_NN"}, // Student required to login to the system and register
						  {"_NN/_NNP/NNS","_VBD/_NNP/_VBZ","_VB","_CC","_VB/_NN"}, //and 6
			  
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_NN"}, //Student would be/will be/should be/must be login to the system. j=0 
						  {"_NN/_NNP/NNS","_MD","_VB","_NN"}, //Student can login to the system 1
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB"},//Student would be/will be/should be/must be login.	2
					 	  {"_NN/_NNP/_NNS","_MD","_VB"}, //Student can login 			  
					 	  //{"_NN/_NNP/NNS","_NN/_NNS/_VBP","_IN","_NN/_NNS"}, // Manager register/s for courses. *-*
					 	  {" "},
					 	  {"_NN/_NNP/_NNS","_VBD/_NNP/_VBZ","_VB","_NN"}, // Student required to login to the system. 5	  
					 	  {"_NN/_NNP/NNS","_VBZ","_NN","_IN/_TO","_RB/_VB/_JJ"}, //manager has capablility of  delete records.
					 	  
					 	  {"_NN/_NNP/NNS","_VBD/_NNP/_VBZ","_VB"}, //Student required to login. 6
					 	  {"_NN/_NNP/NNS","_VBZ/_VBP","_VB"}, //Student want/s to login
					 	  
					 	  {"_NN/_NNP/NNS","_JJ","_RB/_VB/_JJ"}, //20manager is capable of  delete records.
					 	  {"PRP","_MD/","_VB"} //21 He can login
						};
	
						
	//sentences Detector Model
	InputStream is = new FileInputStream(path+"en-sent.zip");
	SentenceModel model = new SentenceModel(is);
	SentenceDetectorME sdetector = new SentenceDetectorME(model);
	
	//POS tag Model
	POSModel model1 = new POSModelLoader().load(new File(path+"en-pos-maxent.zip"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model1);			
	perfMon.start();
	
	//Tokenize Model  
	InputStream is1 = new FileInputStream(path+"en-token.zip");		 
	TokenizerModel model2 = new TokenizerModel(is1); 
	Tokenizer tokenizer = new TokenizerME(model2);
	
	//Detokenize
	SimpleTokenizer instance = SimpleTokenizer.INSTANCE;
			
	String sentences[] = sdetector.sentDetect(Input); //Paragraph to Sentences
	//String sentences[] = new String [sentences1.length];
	
	
	for( String oneLine : sentences){ // Take Sentences by Sentences
		oneLine= oneLine.replace(".com", "B&B");   
		oneLine= oneLine.replace(".", "");
		oneLine= oneLine.replace(",", "");   
		oneLine= oneLine.replace("B&B", ".com");   
		
	ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(oneLine)); //a line	
	String line;
		
		while ( (line = lineStream.read()) != null) { 
		whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line); //Word
		String[] tags = tagger.tag(whitespaceTokenizerLine);	 //Tag
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags); // Word plus Tag
		System.out.println(sample.toString());
		
		// Start Grammar Check
		String[] tokenizedsentence = tokenizer.tokenize(sample.toString());
		

		int j=0;
		int k =0;
		int[] matches;
	    String[] matchedWord = null;
		boolean found=false;
	
		
		for(j=0;j<Grammar.length;j++){   // take one grammar and check, so on.
			
			int missCount=0;
			int hitCount=0;
			k=0;
			//base case
			if(found)
				break;
 
			//sentence is shorter than the Rule - change the rule
			if(tokenizedsentence.length<Grammar[j].length)
				continue;
			
			matches=new int[Grammar[j].length];
		    matchedWord= new String[Grammar[j].length];
			
		    int h= tokenizedsentence.length;
		    if(oneLine.contains("capability")){
		    	//System.out.println("contains Capability");
				h--;
		    }
		    
			//check each token(word) in the sentence
			for(int i=0; i<h;i++){
				
					if(found)
						break;

						String[] alternatives= Grammar[j][k].split("/");
						
						// for each alternate tag  
						for(String alt : alternatives){
							
											if(tokenizedsentence[i].endsWith(alt)){
										
								//map the matched tags - Actor/Action analysis 
								
								matches[k]=i;
							    matchedWord[k]=whitespaceTokenizerLine[i];
								hitCount++;
														
								//rule is complete!
								if(hitCount==Grammar[j].length)
								{
									
									System.out.println("found a match rule no#"+j);
									found=true;
									
									ReqSet.add(oneLine);
									
									System.out.println(entity);
									Caseconstruct(whitespaceTokenizerLine,matchedWord,matches,j,"Verify that ",entity);
									
									entity=	matchedWord[0];							
									break;
								}
								else	
									k++;
								// an alternate is needed only once
								break;
									
							}											
						}						
				}				
			}		
		perfMon.incrementCounter(); 
	  }
	}
	perfMon.stopAndPrintFinalResult();
 }
	
		
	


public static void  Detokenize(String[] ToknArry, String wordAdd, String textResult ) throws IOException {
	 
	
	//textResult.setText("\n"+"\n");
	Operation operations[] = new Operation[ToknArry.length];
	String oper = "MOVE_RIGHT"; // please refer above list for the list of operations ss
	for (int i = 0; i < ToknArry.length; i++){
		operations[i] = Operation.parse(oper); 
		}
	
	System.out.println(operations.length); 
	DetokenizationDictionary detokenizeDict = new DetokenizationDictionary(ToknArry, operations);
	DictionaryDetokenizer dictDetokenize = new DictionaryDetokenizer(detokenizeDict);
	String st = dictDetokenize.detokenize(ToknArry," ");
	System.out.println(wordAdd +st);
	//textResult.append(wordAdd +st + "\n");
	
}



public static void  Caseconstruct(String[] whitspc, String[] matchwrd,int[] matchindx, int jj, String addtext, String actr ) throws IOException {

	if(jj==0){

		TestSet.add(addtext+matchwrd[0]+" is able to "+matchwrd[3]+".");
		TestSet.add(addtext+matchwrd[0]+" can "+matchwrd[7]+".");
		
	} 
	
	
	if(jj==1){

		TestSet.add(addtext+matchwrd[0]+" is able to "+matchwrd[3]+".");
		TestSet.add(addtext+matchwrd[0]+" can "+matchwrd[6]+".");

	}
	
	if(jj==2){
		TestSet.add(addtext+ matchwrd[0]+" can "+matchwrd[2]+".");
		TestSet.add(addtext+ matchwrd[0]+" is able to "+matchwrd[5]+".");		
	} 

	if(jj==3){
		TestSet.add(addtext+matchwrd[0]+" is able to "+matchwrd[2]+".");
		TestSet.add(addtext+matchwrd[0]+" can "+matchwrd[6]+".");		
	} 

	if(jj==4){
	 	String wword="";
		
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[3]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword); //Student should be/must be login to the system. j=0 
	} 
	
	else if (jj==5){		
		
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);//Student can login to the system
		} 
	
	else if(jj==6){
		String wword="";
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" "+wword);
		//textResult.setText("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" "+wword);  // Student should/ must be login
	}   
	
	else if(jj==7){   
		
		String wword="";
		
		for (int i=matchindx[1]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		
		TestSet.add("Verify that " +matchwrd[0]+" is able to "+wword);
		//textResult.setText("Verify that " +matchwrd[0]+" can "+matchwrd[2]+" "+matchwrd[3]+" "+ matchwrd[4]);   //Student can login
	} 

	else if(jj==8){                                                               
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]+" "+matchwrd[5]);
		//textResult.setText("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]+" "+matchwrd[5]);   //Student register/s for courses 
	}
	
	else if(jj==9){

		String wword="";
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		
		TestSet.add("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);   /// Student required to login to the system. 5	   
	}
	
	else if(jj==10){
		
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4] );   //Student register/s for courses 6
	}
	

	else if(jj==11){
		
	 	String wword="";	
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword); //Student should be/must be login to the system. j=0 
	
	}
	
	else if (jj==12){		
		
		String wword="";
		//{"_NN/_NNP/NNS","_MD","_VB","_NN"},
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[2]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);//Student can login to the system
		} 
	
	else if(jj==13){
		TestSet.add("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]);
		//textResult.setText("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" 2" );  // Student should/ must be login
	}
	
	else if(jj==14){
	String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that " +matchwrd[0]+" is able to "+matchwrd[2]+wword);
		//textResult.setText("Verify that " +matchwrd[0]+" can "+matchwrd[2]+ " 3" );   //Student can login
	}

	else if(jj==15){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" can "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+ " 4");   //Student register/s for courses 
	} //----
	
	else if(jj==16){

		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		
		TestSet.add("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);   /// Student required to login to the system. 5	   
	}
	
	else if(jj==17){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}   
			
		TestSet.add("Verify that "+matchwrd[0]+" can "+ wword);
		//{"_NN/_NNP/NNS","_VBZ","_NN","_RB/_VB/_JJ"} // manager has capablility of  delete records.
	}
	
	else if(jj==18){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i]; 
		}
		
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+ " 5");   //Student register/s for courses 6
		
	}
	
	else if(jj==19){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
			
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+ " 5");   //Student register/s for courses 6
		
	}
	
	else if(jj==20){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
			
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[2]+ wword);
		
	}
	
	else if(jj==21){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
			
		TestSet.add("Verify that "+actr+ wword);
		
	}
		
}




public void RuleMapNonFunc(String paragraph, String textResult) throws IOException {
	
	String whitespaceTokenizerLine[];
	String Input = paragraph;
	
	String[][] Grammar = {
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_NN"}, //system should be able to handle 5000 of users at a time.
						  {"_NN/_NNP/_NNS","_VBZ/_VBP","_VB/_NN"}, //system needs to response
						  
						  
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_NN","_CC","_VB/_NN"}, //and Student would be/will be/should be/must be login to the system. j=0 
						  {"_NN/_NNP/NNS","_MD","_VB","_NN","_CC","_VB/_NN"}, // 1
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_CC","_VB/_NN"}, //Student would be/will be/should be/must be login and register.	2
						  {"_NN/_NNP/_NNS","_MD","_VB","_CC","_VB/_NN"}, //Student can login and register
						  {"_NN/_NNP/NNS","_NN/_NNS","_IN","_NN/_NNS","_CC","_VB/_NN"}, // Manager register/s for courses and login.
						  {"_NN/_NNP/_NNS","_VBD/_NNP/_VBZ","_VB","_NN","_CC","_VB/_NN"}, // Student required to login to the system and register
						  {"_NN/_NNP/NNS","_VBD/_NNP/_VBZ","_VB","_CC","_VB/_NN"}, //and 6
			  
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB","_NN"}, //Student would be/will be/should be/must be login to the system. j=0 
						  {"_NN/_NNP/NNS","_MD","_VB","_NN"}, //Student can login to the system 1
						  {"_NN/_NNP/_NNS","_MD","_VB","_VB"},//Student would be/will be/should be/must be login.	2
					 	  {"_NN/_NNP/_NNS","_MD","_VB"}, //Student can login 			  
					 	  //{"_NN/_NNP/NNS","_NN/_NNS/_VBP","_IN","_NN/_NNS"}, // Manager register/s for courses. *-*
					 	  {"_NN/_NNP/_NNS","_VBD/_NNP/_VBZ","_VB","_NN"}, // Student required to login to the system. 5	  
						  {"_NN/_NNP/NNS","_VBD/_NNP/_VBZ","_VB"}, // Student required to login. 6
					 	  {"_NN/_NNP/NNS","_VBZ/_VBP","_VB"} //Student want/s to login
						   //should _NN/_NNP/NNS,_VBP,_VB
						  
						  
					 		};
	
						
	//sentences Detector Model
	InputStream is = new FileInputStream("E:\\eProjects\\Dplus Project\\DesignPlus v1.4\\src\\en-sent.zip");
	SentenceModel model = new SentenceModel(is);
	SentenceDetectorME sdetector = new SentenceDetectorME(model);
	
	//POS tag Model
	POSModel model1 = new POSModelLoader().load(new File("E:\\eProjects\\Dplus Project\\DesignPlus v1.4\\src\\en-pos-maxent.zip"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model1);			
	perfMon.start();
	
	//Tokenize Model  
	InputStream is1 = new FileInputStream("E:\\eProjects\\Dplus Project\\DesignPlus v1.4\\src\\en-token.zip");		 
	TokenizerModel model2 = new TokenizerModel(is1); 
	Tokenizer tokenizer = new TokenizerME(model2);
	
	//Detokenize
	SimpleTokenizer instance = SimpleTokenizer.INSTANCE;
			
	String sentences[] = sdetector.sentDetect(Input); //Paragraph to Sentences
	
	for( String oneLine : sentences){ // Take Sentences by Sentences
		//oneLine= oneLine.replace(".", "");   
		//oneLine= oneLine.replace(",", "");   
		
	ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(oneLine)); //a line	
	String line;
		
		while ( (line = lineStream.read()) != null) { 
		whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line); //Word
		String[] tags = tagger.tag(whitespaceTokenizerLine);	 //Tag
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags); // Word plus Tag
		System.out.println(sample.toString());
		
		// Start Grammar Check
		String[] tokenizedsentence = tokenizer.tokenize(sample.toString());
		String[] tokenizedsentence1 = tokenizer.tokenize(sentences.toString());
		

		int j=0;
		int k =0;
		int[] matches;
	    String[] matchedWord = null;
		boolean found=false;
	
		
		for(j=0;j<Grammar.length;j++){   // take one grammar and check, so on.
			
			int missCount=0;
			int hitCount=0;
			k=0;
			//base case
			if(found)
				break;

			//sentence is shorter than the Rule - change the rule
			if(tokenizedsentence.length<Grammar[j].length)
				continue;
			matches=new int[Grammar[j].length];
		    matchedWord= new String[Grammar[j].length];
			
			//check each token(word) in the sentence
			for(int i=0; i<tokenizedsentence.length;i++){   
					if(found)
						break;
			
					// multi-valued tag
					if(Grammar[j][k].contains("/")){
						String[] alternatives= Grammar[j][k].split("/");
						
						// for each alternate tag
						for(String alt : alternatives){
							System.out.println("the alt is > "+alt);
							
							
											if(tokenizedsentence[i].endsWith(alt)){
										System.out.println("alt hit");
										
								//map the matched tags - Actor/Action analysis 
								matches[k]=i;
							    matchedWord[k]=whitespaceTokenizerLine[i];
								hitCount++;
														
								//rule is complete!
								if(hitCount==Grammar[j].length)
								{
									
									System.out.println("found a match rule no#"+j);
									found=true;
									
									CaseconstructNonFunc(whitespaceTokenizerLine,matchedWord,matches,j,textResult,"Verify that ");
																	
									break;
								}
								else	
									k++;
								// an alternate is needed only once
								break;
									
							}		
							//alternate is not yet found-------
							else{
								System.out.println("alt missed");
								missCount++;
							
								if(missCount>hitCount && missCount>Grammar.length/2){
								
									System.out.println("must be skipped!");
//									break;
								}
								

							}
											
						}	
						
					}					
					// regular Match! - not multi valued
						else if(tokenizedsentence[i].endsWith(Grammar[j][k])){					
					//map the matched tags - Actor/Action analysis 
							matches[k]=i;
							matchedWord[k]=whitespaceTokenizerLine[i];
							hitCount++;

					//rule is complete!
					if(hitCount==Grammar[j].length)
					{
						System.out.println("found a match rule no#"+j);
						found=true;
						
						CaseconstructNonFunc(whitespaceTokenizerLine,matchedWord,matches,j,textResult,"Verify that ");
															
						break;
					}
					
					else
						k++;
				}	
				else{
						missCount++;
						
						if(missCount>hitCount && missCount>tokenizedsentence.length/2){
							
							System.out.println("must be skipped!");
							//break;
						}
			    	}	
				}				
			}		
		perfMon.incrementCounter(); 
	  }
	}
	perfMon.stopAndPrintFinalResult();
 }
	
		
public static void  CaseconstructNonFunc(String[] whitspc, String[] matchwrd,int[] matchindx, int jj, String textResult , String addtext ) throws IOException {

	if(jj==0){
	 	String wword="";
		
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}		
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword); //Student should be/must be login to the system. j=0 
	} 
	
	else if(jj==1){
	 	String wword="";
		
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}		
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[2]+" "+ wword); //Student should be/must be login to the system. j=0 
	} 
	
	
	//---
	

	if(jj==2){
	 	String wword="";
		
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){;
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword); //Student should be/must be login to the system. j=0 
	} 
	
	else if (jj==3){		
		
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);//Student can login to the system
		} 
	
	else if(jj==4){
		String wword="";
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" "+wword);
		//textResult.setText("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" "+wword);  // Student should/ must be login
	}   
	
	else if(jj==5){
		TestSet.add("Verify that " +matchwrd[0]+" can "+matchwrd[2]+" "+matchwrd[3]+" "+ matchwrd[4]);
		//textResult.setText("Verify that " +matchwrd[0]+" can "+matchwrd[2]+" "+matchwrd[3]+" "+ matchwrd[4]);   //Student can login
	} 

	else if(jj==6){ 
		TestSet.add("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]+" "+matchwrd[5]);
		//textResult.setText("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]+" "+matchwrd[5]);   //Student register/s for courses 
	}
	
	else if(jj==7){

		String wword="";
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		
		TestSet.add("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);   /// Student required to login to the system. 5	   
	}
	
	else if(jj==8){
		
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4]);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+" "+matchwrd[3]+" "+matchwrd[4] );   //Student register/s for courses 6
	}
	

	
	//*-*-*-*-*--
	
	else if(jj==9){
	 	String wword="";
		
		
		for (int i=matchindx[3]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[3]+" "+ wword); //Student should be/must be login to the system. j=0 
	}
	
	else if (jj==10){		
		
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+matchwrd[1]+" "+matchwrd[2]+" "+ wword);//Student can login to the system
		} 
	
	else if(jj==11){
		TestSet.add("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]);
		//textResult.setText("Verify that " + matchwrd[0]+" is able to "+matchwrd[3]+" 2" );  // Student should/ must be login
	}
	
	else if(jj==12){
		TestSet.add("Verify that " +matchwrd[0]+" can "+matchwrd[2]);
		//textResult.setText("Verify that " +matchwrd[0]+" can "+matchwrd[2]+ " 3" );   //Student can login
	}

	else if(jj==13){
		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		TestSet.add("Verify that "+matchwrd[0]+" can "+ wword);
		//textResult.setText("Verify that "+matchwrd[0]+" can "+matchwrd[1]+" "+matchwrd[2]+" "+matchwrd[3]+ " 4");   //Student register/s for courses 
	} //----
	
	else if(jj==14){

		String wword="";
		
		for (int i=matchindx[2]+1; i<whitspc.length;i++){
			 wword = wword + " "+ whitspc[i];
		}
		
		TestSet.add("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);
		//textResult.setText("Verify that "+matchwrd[0]+" "+"is able to "+matchwrd[2]+" "+wword);   /// Student required to login to the system. 5	   
	}
	
	else if(jj==15){
		TestSet.add("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]);
		//textResult.setText("Verify that "+matchwrd[0]+" is able to "+matchwrd[2]+ " 5");   //Student register/s for courses 6
		}
}





//v4
}