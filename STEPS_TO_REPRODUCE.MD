Ensure you checkout the correct branch: ok/repr_with_k2

It's necessary to publish a library to maven (mavenLocal). 
Otherwise the issue doesn't reproduce with a module in the project.
`./gradlew :libA:publishToMavenLocal`

Comment these two lines in gradle.properties:
```
kotlin.native.cacheKind=none
compose.kotlin.native.manageCacheKind=false
```

Run:

```
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

The last part of stacktrace:

```
Caused by: java.lang.IllegalStateException: FIELD name:org_testkotlin2_libA_LibAClass$stable type:kotlin.Int visibility:public [final,static]
        at org.jetbrains.kotlin.backend.konan.llvm.LlvmDeclarations.forStaticField(LlvmDeclarations.kt:53)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.staticFieldPtr(IrToBitcode.kt:1871)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateGetField(IrToBitcode.kt:1767)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateExpression(IrToBitcode.kt:983)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateExpression$default(IrToBitcode.kt:971)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateExplicitArgs(IrToBitcode.kt:2366)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateCall(IrToBitcode.kt:2237)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateExpression(IrToBitcode.kt:975)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.evaluateExpression$default(IrToBitcode.kt:971)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.initGlobalField(IrToBitcode.kt:402)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.buildInitializerFunctions(IrToBitcode.kt:427)
        at org.jetbrains.kotlin.backend.konan.llvm.CodeGeneratorVisitor.runAndProcessInitializers(IrToBitcode.kt:458)
```


Keeping these 2 lines in gradle.properties enabled makes it work (the task takes much longer):

```
kotlin.native.cacheKind=none
compose.kotlin.native.manageCacheKind=false
```