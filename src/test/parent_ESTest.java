//package test;
///*
// * This file was automatically generated by EvoSuite
// * Sat Aug 19 16:41:03 GMT 2017
// */
//
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.evosuite.runtime.EvoAssertions.*;
//import org.evosuite.runtime.EvoRunner;
//import org.evosuite.runtime.EvoRunnerParameters;
//import org.junit.runner.RunWith;
//import example.*;
//
//@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
//public class parent_ESTest extends parent_ESTest_scaffolding {
//
//  @Test(timeout = 4000)
//  public void test0()  throws Throwable  {
//      parent parent0 = new parent();
//      parent0.child1obj = null;
//      // Undeclared exception!
//      try { 
//        parent0.method12((child1) null, 0);
//        fail("Expecting exception: NullPointerException");
//      
//      } catch(NullPointerException e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//         verifyException("parent", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test1()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.child1obj = null;
//      // Undeclared exception!
//      try { 
//        parent0.method11(child1_0, 1934);
//        fail("Expecting exception: NullPointerException");
//      
//      } catch(NullPointerException e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//         verifyException("parent", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test2()  throws Throwable  {
//      parent parent0 = new parent();
//      parent0.child1obj = null;
//      // Undeclared exception!
//      try { 
//        parent0.method1((child1) null, 0);
//        fail("Expecting exception: NullPointerException");
//      
//      } catch(NullPointerException e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//         verifyException("parent", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test3()  throws Throwable  {
//      parent parent0 = new parent();
//      child1 child1_0 = parent0.child1obj;
//      parent0.method11(child1_0, (-21));
//  }
//
//  @Test(timeout = 4000)
//  public void test4()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.method12(child1_0, (-960));
//  }
//
//  @Test
//  public void test5()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.method1(child1_0, (-2184));
//  }
//
//  @Test(timeout = 4000)
//  public void test6()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.method2();
//  }
//
//  @Test(timeout = 4000)
//  public void test7()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.method22();
//  }
//
//  @Test(timeout = 4000)
//  public void test8()  throws Throwable  {
//      child1 child1_0 = new child1();
//      parent parent0 = child1_0.ChildMethod3();
//      parent0.method21();
//  }
//}
