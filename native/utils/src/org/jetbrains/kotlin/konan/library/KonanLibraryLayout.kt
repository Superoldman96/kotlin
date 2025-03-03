/**
 * Copyright 2010-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.konan.library

import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.target.KonanTarget
import org.jetbrains.kotlin.library.IrKotlinLibraryLayout
import org.jetbrains.kotlin.library.KotlinLibraryLayout
import org.jetbrains.kotlin.library.MetadataKotlinLibraryLayout

const val KLIB_TARGETS_FOLDER_NAME = "targets"

interface TargetedKotlinLibraryLayout : KotlinLibraryLayout {
    val target: KonanTarget?
        // This is a default implementation. Can't make it an assignment.
        get() = null
    val targetsDir
        get() = File(componentDir, KLIB_TARGETS_FOLDER_NAME)
    val targetDir
        get() = File(targetsDir, target!!.visibleName)
    val includedDir
        get() = File(targetDir, "included")
}

interface BitcodeKotlinLibraryLayout : TargetedKotlinLibraryLayout, KotlinLibraryLayout {
    val nativeDir
        get() = File(targetDir, "native")
}

interface KonanLibraryLayout : MetadataKotlinLibraryLayout, BitcodeKotlinLibraryLayout, IrKotlinLibraryLayout
