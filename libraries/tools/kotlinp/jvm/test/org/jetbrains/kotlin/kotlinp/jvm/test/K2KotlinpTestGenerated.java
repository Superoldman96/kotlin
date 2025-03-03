/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kotlinp.jvm.test;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.kotlinp.jvm.test.GenerateKotlinpTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("libraries/tools/kotlinp/jvm/testData")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class K2KotlinpTestGenerated extends AbstractK2KotlinpTest {
  private void runTest(String testDataFilePath) {
    KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
  }

  public void testAllFilesPresentInTestData() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("libraries/tools/kotlinp/jvm/testData"), Pattern.compile("^(.*)\\.kts?$"), null, true);
  }

  @TestMetadata("AnnotationTargets.kt")
  public void testAnnotationTargets() {
    runTest("libraries/tools/kotlinp/jvm/testData/AnnotationTargets.kt");
  }

  @TestMetadata("AnnotationWithQualifiedExpressionInArgument.kt")
  public void testAnnotationWithQualifiedExpressionInArgument() {
    runTest("libraries/tools/kotlinp/jvm/testData/AnnotationWithQualifiedExpressionInArgument.kt");
  }

  @TestMetadata("Annotations.kt")
  public void testAnnotations() {
    runTest("libraries/tools/kotlinp/jvm/testData/Annotations.kt");
  }

  @TestMetadata("Constants.kt")
  public void testConstants() {
    runTest("libraries/tools/kotlinp/jvm/testData/Constants.kt");
  }

  @TestMetadata("ContextReceivers.kt")
  public void testContextReceivers() {
    runTest("libraries/tools/kotlinp/jvm/testData/ContextReceivers.kt");
  }

  @TestMetadata("Contracts.kt")
  public void testContracts() {
    runTest("libraries/tools/kotlinp/jvm/testData/Contracts.kt");
  }

  @TestMetadata("Delegation.kt")
  public void testDelegation() {
    runTest("libraries/tools/kotlinp/jvm/testData/Delegation.kt");
  }

  @TestMetadata("EnumEntries.kt")
  public void testEnumEntries() {
    runTest("libraries/tools/kotlinp/jvm/testData/EnumEntries.kt");
  }

  @TestMetadata("FunInterface.kt")
  public void testFunInterface() {
    runTest("libraries/tools/kotlinp/jvm/testData/FunInterface.kt");
  }

  @TestMetadata("IntersectionTypeInLambdaLiteralAndDelegatedProperty.kt")
  public void testIntersectionTypeInLambdaLiteralAndDelegatedProperty() {
    runTest("libraries/tools/kotlinp/jvm/testData/IntersectionTypeInLambdaLiteralAndDelegatedProperty.kt");
  }

  @TestMetadata("Lambda.kt")
  public void testLambda() {
    runTest("libraries/tools/kotlinp/jvm/testData/Lambda.kt");
  }

  @TestMetadata("LocalDelegatedProperties.kt")
  public void testLocalDelegatedProperties() {
    runTest("libraries/tools/kotlinp/jvm/testData/LocalDelegatedProperties.kt");
  }

  @TestMetadata("MultiFileClass.kt")
  public void testMultiFileClass() {
    runTest("libraries/tools/kotlinp/jvm/testData/MultiFileClass.kt");
  }

  @TestMetadata("NestedClasses.kt")
  public void testNestedClasses() {
    runTest("libraries/tools/kotlinp/jvm/testData/NestedClasses.kt");
  }

  @TestMetadata("NestedTypeAlias.kt")
  public void testNestedTypeAlias() {
    runTest("libraries/tools/kotlinp/jvm/testData/NestedTypeAlias.kt");
  }

  @TestMetadata("NotEnumWithEnumEntriesEnabled.kt")
  public void testNotEnumWithEnumEntriesEnabled() {
    runTest("libraries/tools/kotlinp/jvm/testData/NotEnumWithEnumEntriesEnabled.kt");
  }

  @TestMetadata("OptionalAnnotation.kt")
  public void testOptionalAnnotation() {
    runTest("libraries/tools/kotlinp/jvm/testData/OptionalAnnotation.kt");
  }

  @TestMetadata("PlatformType.kt")
  public void testPlatformType() {
    runTest("libraries/tools/kotlinp/jvm/testData/PlatformType.kt");
  }

  @TestMetadata("Properties.kt")
  public void testProperties() {
    runTest("libraries/tools/kotlinp/jvm/testData/Properties.kt");
  }

  @TestMetadata("scriptSimple.kts")
  public void testScriptSimple() {
    runTest("libraries/tools/kotlinp/jvm/testData/scriptSimple.kts");
  }

  @TestMetadata("SimpleClass.kt")
  public void testSimpleClass() {
    runTest("libraries/tools/kotlinp/jvm/testData/SimpleClass.kt");
  }

  @TestMetadata("SimplePackage.kt")
  public void testSimplePackage() {
    runTest("libraries/tools/kotlinp/jvm/testData/SimplePackage.kt");
  }

  @TestMetadata("SyntheticClass.kt")
  public void testSyntheticClass() {
    runTest("libraries/tools/kotlinp/jvm/testData/SyntheticClass.kt");
  }

  @TestMetadata("TypeAlias.kt")
  public void testTypeAlias() {
    runTest("libraries/tools/kotlinp/jvm/testData/TypeAlias.kt");
  }

  @TestMetadata("TypeParameters.kt")
  public void testTypeParameters() {
    runTest("libraries/tools/kotlinp/jvm/testData/TypeParameters.kt");
  }

  @TestMetadata("UseTypeTable.kt")
  public void testUseTypeTable() {
    runTest("libraries/tools/kotlinp/jvm/testData/UseTypeTable.kt");
  }

  @TestMetadata("ValueClass.kt")
  public void testValueClass() {
    runTest("libraries/tools/kotlinp/jvm/testData/ValueClass.kt");
  }

  @TestMetadata("VarargInAnnotation.kt")
  public void testVarargInAnnotation() {
    runTest("libraries/tools/kotlinp/jvm/testData/VarargInAnnotation.kt");
  }

  @TestMetadata("VersionRequirement.kt")
  public void testVersionRequirement() {
    runTest("libraries/tools/kotlinp/jvm/testData/VersionRequirement.kt");
  }

  @TestMetadata("libraries/tools/kotlinp/jvm/testData/jvmDefault")
  @TestDataPath("$PROJECT_ROOT")
  @RunWith(JUnit3RunnerWithInners.class)
  public static class JvmDefault extends AbstractK2KotlinpTest {
    private void runTest(String testDataFilePath) {
      KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInJvmDefault() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("libraries/tools/kotlinp/jvm/testData/jvmDefault"), Pattern.compile("^(.*)\\.kts?$"), null, true);
    }

    @TestMetadata("Disable.kt")
    public void testDisable() {
      runTest("libraries/tools/kotlinp/jvm/testData/jvmDefault/Disable.kt");
    }

    @TestMetadata("Enable.kt")
    public void testEnable() {
      runTest("libraries/tools/kotlinp/jvm/testData/jvmDefault/Enable.kt");
    }

    @TestMetadata("NoCompatibility.kt")
    public void testNoCompatibility() {
      runTest("libraries/tools/kotlinp/jvm/testData/jvmDefault/NoCompatibility.kt");
    }

    @TestMetadata("withCompatibility.kt")
    public void testWithCompatibility() {
      runTest("libraries/tools/kotlinp/jvm/testData/jvmDefault/withCompatibility.kt");
    }

    @TestMetadata("withoutCompatibility.kt")
    public void testWithoutCompatibility() {
      runTest("libraries/tools/kotlinp/jvm/testData/jvmDefault/withoutCompatibility.kt");
    }
  }

  @TestMetadata("libraries/tools/kotlinp/jvm/testData/localClasses")
  @TestDataPath("$PROJECT_ROOT")
  @RunWith(JUnit3RunnerWithInners.class)
  public static class LocalClasses extends AbstractK2KotlinpTest {
    private void runTest(String testDataFilePath) {
      KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInLocalClasses() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("libraries/tools/kotlinp/jvm/testData/localClasses"), Pattern.compile("^(.*)\\.kts?$"), null, true);
    }

    @TestMetadata("AnonymousObject.kt")
    public void testAnonymousObject() {
      runTest("libraries/tools/kotlinp/jvm/testData/localClasses/AnonymousObject.kt");
    }

    @TestMetadata("DeepInnerLocalChain.kt")
    public void testDeepInnerLocalChain() {
      runTest("libraries/tools/kotlinp/jvm/testData/localClasses/DeepInnerLocalChain.kt");
    }

    @TestMetadata("LocalClassInConstructor.kt")
    public void testLocalClassInConstructor() {
      runTest("libraries/tools/kotlinp/jvm/testData/localClasses/LocalClassInConstructor.kt");
    }

    @TestMetadata("LocalClassInSignature.kt")
    public void testLocalClassInSignature() {
      runTest("libraries/tools/kotlinp/jvm/testData/localClasses/LocalClassInSignature.kt");
    }
  }
}
