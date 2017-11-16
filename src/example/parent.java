package example;

import java.util.List;

import com.parteek.jebra.methods.MethodStructure;
import com.parteek.jebra.methods.TestMethodStructure;
import com.parteek.jebra.statements.JebraStmts;

import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.stmt.Statement;

public class parent {

	public child1 child1obj =null;
	public parent() {
		// TODO Auto-generated constructor stub
		child1obj = new child1();
		child1 childObj = new child1();
		int aaa =0;
	}

	public Integer MixedMethod(child1 c1,int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		List<? extends Object> returnMethod = method.getAllTestMethods() != null ? method.getAllTestMethods() : method.getJebraStmts();
		
		List<TestMethodStructure> arrayList = method.getAllTestMethods();
		for (TestMethodStructure structure : method.getAllTestMethods()) {
			String str1= structure.getReturnType();
			System.out.println(str1);
			if (1 == child1obj.ChildMethod2()) {
				String str2 = structure.getMethodDeclationStmt();
				try {
					ConstructorDeclaration consbetweenTry1inside= method.getMyConstDeclaration();
					Integer ss1 = consbetweenTry1inside.getBeginLine();
					for (int i = 0; i < arrayList.size(); i++) {
						TestMethodStructure testMethodStructure = arrayList.get(i);
						String str11= testMethodStructure.getReturnType();
						System.out.println(str11);
					}
				}
				catch(NullPointerException e1) {
					System.out.println(e1);
					Exception exceptionCatch1inside = new Exception();
					Throwable error =  exceptionCatch1inside.getCause();
				}
				finally {
					MethodStructure methodStructureTry1 = new MethodStructure();
					try {
						ConstructorDeclaration finally_1_Try= method.getMyConstDeclaration();
						Integer intFinally_1_Try = finally_1_Try.getBeginLine();
					}
					catch(NullPointerException efinally) {
						Exception finally_1_CATCH = new Exception();
						Throwable errorfinal =  finally_1_CATCH.getCause();
					}
					finally {
						MethodStructure finally_1_Finally = new MethodStructure();
						List<TestMethodStructure> objfinal =  finally_1_Finally.getAllTestMethods();
					}
					List<TestMethodStructure> objTry1 =  methodStructureTry1.getAllTestMethods();
				}
			}
			else {
				String ddd = structure.getName();
			}
		}
		
		this.temp();
		return 1;
	}
	
/*	public void ConditionalMethod(child1 c1,int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		List<? extends Object> returnMethod = method.getAllTestMethods() != null ? method.getBodyStmts() : method.getJebraStmts();
		
		List<? extends Object> objects= method.isContructor() != true ? method.getJebraParameters() : method.getJebraStmts();
		this.temp();
	}*/

//	public void forLoopMethod(child1 c1,int answer) {
//		String str = "abc";
//		MethodStructure method = new MethodStructure();
////		List<TestMethodStructure> arrayList = method.getAllTestMethods();
////		for (int i = 0; i < arrayList.size(); i++) {
////			TestMethodStructure testMethodStructure = arrayList.get(i);
////			String str1= testMethodStructure.getReturnType();
////			System.out.println(str1);
////		}
//		for (int i = 0; i < method.getAllTestMethods().size(); i++) {
//			TestMethodStructure testMethodStructure = method.getAllTestMethods().get(i);
//			String str1= testMethodStructure.getReturnType();
//			System.out.println(str1);
//		}
//		this.temp();
//	}
	
/*	public void foreachLoopMethod(child1 c1,int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		List<TestMethodStructure> arrayList =  method.getAllTestMethods();
		for (TestMethodStructure testMethodStructure : method.getAllTestMethods()) {
			String str1= testMethodStructure.getReturnType();
			System.out.println(str1);
			if (1 == child1obj.ChildMethod2()) {
				String str2 = testMethodStructure.getMethodDeclationStmt();
			}
			else {
				String ddd = testMethodStructure.getName();
			}
		}
		this.temp();
	}
	*/
/*	public void doWhileMethod(child1 c1, int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		do {
			ConstructorDeclaration cons = method.getMyConstDeclaration();
			Integer ss = cons.getBeginLine();
		} while (method.getAllTestMethods() != null);
		this.temp();
	}
	
	public void whileMethod(child1 c1, int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		while (method.getAllTestMethods() != null) {
			ConstructorDeclaration cons = method.getMyConstDeclaration();
			Integer ss = cons.getBeginLine();
		}
		this.temp();
	}*/
	
	public void helloMethodTrySimple( int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		ConstructorDeclaration consTry1= method.getMyConstDeclaration();
		Integer ssTry1 = consTry1.getBeginLine();
		try {
			ConstructorDeclaration consbetweenTry1inside= method.getMyConstDeclaration();
			Integer ss1 = consbetweenTry1inside.getBeginLine();
		}
		catch(NullPointerException e1) {
			System.out.println(e1);
			Exception exceptionCatch1inside = new Exception();
			Throwable error =  exceptionCatch1inside.getCause();
		}
		finally {
			MethodStructure methodStructureTry1 = new MethodStructure();
			try {
				ConstructorDeclaration finally_1_Try= method.getMyConstDeclaration();
				Integer intFinally_1_Try = finally_1_Try.getBeginLine();
			}
			catch(NullPointerException efinally) {
				Exception finally_1_CATCH = new Exception();
				Throwable errorfinal =  finally_1_CATCH.getCause();
			}
			finally {
				MethodStructure finally_1_Finally = new MethodStructure();
				List<TestMethodStructure> objfinal =  finally_1_Finally.getAllTestMethods();
			}
			List<TestMethodStructure> objTry1 =  methodStructureTry1.getAllTestMethods();
		}
		Integer ansTry1 = this.temp();
	}
		
	public void helloMethodTry( int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		try {
			ConstructorDeclaration consTry1= method.getMyConstDeclaration();
			Integer ssTry1 = consTry1.getBeginLine();
			try {
				ConstructorDeclaration consbetweenTry1Try2inside= method.getMyConstDeclaration();
				Integer ss1 = consbetweenTry1Try2inside.getBeginLine();
			}
			catch(NullPointerException e1) {
				System.out.println(e1);
				Exception exceptionTry1Catch1inside = new Exception();
				Throwable error =  exceptionTry1Catch1inside.getCause();
			}
			finally {
				MethodStructure methodStructureTry1 = new MethodStructure();
				List<TestMethodStructure> objTry1 =  methodStructureTry1.getAllTestMethods();
			}
			Integer ansTry1 = this.temp();
		}
		catch(NullPointerException e) {
			ConstructorDeclaration topCatch1= method.getMyConstDeclaration();
			Integer Catch1 = topCatch1.getBeginLine();
			try {
				ConstructorDeclaration Catch_1_Try= method.getMyConstDeclaration();
				Integer intCatch1Try = Catch_1_Try.getBeginLine();
			}
			catch(NullPointerException e10) {
				Exception Catch1CATCH = new Exception();
				Throwable error =  Catch1CATCH.getCause();
			}
			finally {
				MethodStructure Catch1Finally = new MethodStructure();
				List<TestMethodStructure> Catch1FinallyObject =  Catch1Finally.getAllTestMethods();
			}
			
			Integer ansCatch_1 = this.temp();
		}
		catch(Exception e) {
			ConstructorDeclaration topCatch2= method.getMyConstDeclaration();
			Integer Catch1 = topCatch2.getBeginLine();
			try {
				ConstructorDeclaration Catch_2_Try= method.getMyConstDeclaration();
				Integer intCatch2Try = Catch_2_Try.getBeginLine();
			}
			catch(NullPointerException e102nd) {
				Exception Catch_2_CATCH = new Exception();
				Throwable error =  Catch_2_CATCH.getCause();
			}
			finally {
				MethodStructure Catch_2_Finally = new MethodStructure();
				List<TestMethodStructure> Catch2FinallyObject =  Catch_2_Finally.getAllTestMethods();
			}
			
			Integer ansCatch_2 = this.temp();
		}
		finally {
			MethodStructure finally_1 = new MethodStructure();
			List<TestMethodStructure> finally_1_Object =  finally_1.getAllTestMethods();
			try {
				ConstructorDeclaration finally_1_Try= method.getMyConstDeclaration();
				Integer intFinally_1_Try = finally_1_Try.getBeginLine();
			}
			catch(NullPointerException efinally) {
				Exception finally_1_CATCH = new Exception();
				Throwable errorfinal =  finally_1_CATCH.getCause();
			}
			finally {
				MethodStructure finally_1_Finally = new MethodStructure();
				List<TestMethodStructure> objfinal =  finally_1_Finally.getAllTestMethods();
			}
			Integer ansfinal_1 = this.temp();
		}
		ConstructorDeclaration conslast= method.getMyConstDeclaration();
		Integer ss = conslast.getBeginLine();
	}

	
	public void helloMethod(child1 c1, int answer) {
		String str = "abc";
		MethodStructure method = new MethodStructure();
		
		if (1 == child1obj.ChildMethod2()) {
			TestMethodStructure if_testobj = new TestMethodStructure();
			if ("if if" == child1obj.ChildMethod1()) {
				MethodStructure if_if_methodStructure = new MethodStructure();
				List<TestMethodStructure> obj =  if_if_methodStructure.getAllTestMethods();
			}
			else {
				child1 if_else_child4 = new child1();
				if_else_child4.ChildMethod2();
			}
			String ss = if_testobj.getClassName();
		}
		else if ("elseif" == child1obj.ChildMethod1()) {
			MethodStructure elseIf_methodStructure1 = new MethodStructure();
			if ("parteek ins" == child1obj.ChildMethod1()) {
				MethodStructure elseIf_methodStructure2 = new MethodStructure();
				List<TestMethodStructure> elseIf_obj3 =  elseIf_methodStructure2.getAllTestMethods();
			}
			else {
				child1 elseIf_else_child42 = new child1();
				elseIf_else_child42.ChildMethod2();
			}
			List<TestMethodStructure> obj =  elseIf_methodStructure1.getAllTestMethods();
		}
		else {
			child1 else_child12 = new child1();
			if ("parteek inside" == child1obj.ChildMethod1()) {
				MethodStructure else_if_methodStructure22 = new MethodStructure();
				List<TestMethodStructure> else_if_obj222 =  else_if_methodStructure22.getAllTestMethods();
			}
			else {
				child1 else_else_child422 = new child1();
				else_else_child422.ChildMethod2();
			}
			else_child12.ChildMethod2();
		}
		ConstructorDeclaration cons= method.getMyConstDeclaration();
		Integer ss = cons.getBeginLine();
		
	}
	

	
	public void helloMethod2(child1 c1,int answer) {
		
		String str = "abc";
		
		MethodStructure method = new MethodStructure();
		List<TestMethodStructure> obj =  method.getAllTestMethods();
		for (TestMethodStructure testMethodStructure : obj) {
			String str1= testMethodStructure.getReturnType();
			System.out.println(str1);
		}
		//Integer ans = this.temp();
		this.temp();
		
	}


/*	public void method1(child1 c1,int answer) {
		
		String str = "abc";
		
		MethodStructure methodStructure = new MethodStructure();
		List<TestMethodStructure> obj =  methodStructure.getAllTestMethods();
		
		if(c1.ChildMethod1().equals("dummy")) {
			int ans = c1.hashCode();
		}
		else if(c1.ChildMethod1().equals("parteek")) {
			JebraStmts jebra = new JebraStmts();
			Expression ss =  jebra.getExpr();
			
		}
		else {
			this.temp();
		}
		
		
	}*/
//	public void method10(child1 c1,int answer) {
//		
//		String str = "abc";
//		
//		MethodStructure methodStructure = new MethodStructure();
//		List<TestMethodStructure> obj =  methodStructure.getAllTestMethods();
//		if(c1.ChildMethod1().equals("dummy")) {
//			int ans = this.temp();
//		}
//		method1(c1, answer);
//		if(str.contains("abc")) {
//			
//			JebraStmts jebra = new JebraStmts();
//			Expression ss =  jebra.getExpr();
//			ss.getBeginColumn();
//		}
//		
//	}
//	
	public Integer temp() {
		return 1;
	}
//	public void method2() {
//		Integer ans=0;
//		String child1str = child1obj.ChildMethod1();
//		if(1==child1obj.ChildMethod2()){
//			TestMethodStructure testobj = new TestMethodStructure();
//			String ss =testobj.getClassName();
//		}else{
//			child1 child2 = new child1();
//			child2.ChildMethod2();
//		}
//		if(ans==child1obj.ChildMethod2()){
//			String child11 = child1obj.ChildMethod4();
//			child1 child01 = new child1();
//		}else if("22"==""){
//			Object d = parent.class;
//		}else{
//			if(child1obj.ChildMethod5()!=null){
//				return ;
//			}
//		}
//		if(ans==child1obj.ChildMethod2()){
//			ans = child1obj.ChildMethod7();
//			if("4"==""){
//				Object d = parent.class;
//			}else{
//				ans = child1obj.ChildMethod8();
//			}
//			ans = child1obj.ChildMethod7();
//			
//		}
//		
//	}
	
//	public void method12(child1 c1,int answer) {
//		Integer ans=0;
//		String child1str = child1obj.ChildMethod1();
//		
//		if("1"==""){
//			child1 child0 = new child1();
//			String ss =child0.ChildMethod1();
//		}
//		else{
//			child1 child2 = new child1();
//			child2.ChildMethod2();
//		}
//		
//		if("2"==""){
//			String child1str1 = child1obj.ChildMethod1();
//			child1 child01 = new child1();
//		}
//		else if("22"==""){
//			Object d = parent.class;
//		}
//		else{
//			if(child1obj.ChildMethod1()!=null){
//				return ;
//			}
//		}
//		
//		if("3"==""){
//			return ;
//		}
//		if("4"==""){
//			Object d = parent.class;
//		}else{
//			ans = child1obj.ChildMethod2();
//		}
//	}
//	public void method22() {
//		
//
//	}
//	
//	public void method11(child1 c1,int answer) {
//		Integer ans=0;
//		String child1str = child1obj.ChildMethod1();
//		
//		if("1"==""){
//			child1 child0 = new child1();
//			String ss= child0.ChildMethod1();
//		}
//		else{
//			child1 child2 = new child1();
//			child2.ChildMethod2();
//		}
//		
//		if("2"==""){
//			String child1str1 = child1obj.ChildMethod1();
//			child1 child01 = new child1();
//		}
//		else if("22"==""){
//			Object d = parent.class;
//		}
//		else{
//			if(child1obj.ChildMethod1()!=null){
//				return ;
//			}
//		}
//		
//		if("3"==""){
//			return ;
//		}
//		if("4"==""){
//			Object d = parent.class;
//		}else{
//			ans = child1obj.ChildMethod2();
//		}
//	}
//	public void method21() {
//		
//
//	}
	
}
///-------------------- need to check this
//String str = "abc";
//MethodStructure method = new MethodStructure();
//try {
//	ConstructorDeclaration cons = method.getMyConstDeclaration();
//	Integer ss = cons.getBeginLine();
//} catch (NullPointerException e) {
//	try {
//		ConstructorDeclaration cons = method.getMyConstDeclaration();
//		Integer ss = cons.getBeginLine();
//	} catch (NullPointerException e1) {
//		System.out.println(e);
//	}
//} catch (Exception e) {
//	System.out.println(e);
//} finally {
//	MethodStructure methodStructure = new MethodStructure();
//	List<TestMethodStructure> obj = methodStructure.getAllTestMethods();
//	Integer ans = this.temp();
//}
//TestMethodStructure testobj = new TestMethodStructure();
//String ss = testobj.getClassName();