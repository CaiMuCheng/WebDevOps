package com.mucheng.web.devops.openapi.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.lingala.zip4j.io.inputstream.ZipInputStream
import net.lingala.zip4j.model.LocalFileHeader
import java.io.File
import java.io.FileOutputStream
import java.math.BigDecimal
import java.nio.ByteBuffer
import java.nio.channels.Channels

object FileUtil {

    fun deleteFile(file: File) {
        if (!file.exists()) {
            return
        }

        if (file.isFile) {
            file.delete()
            return
        }

        if (file.isDirectory) {
            val listFiles = file.listFiles() ?: emptyArray()
            for (treeFile in listFiles) {
                deleteFile(treeFile)
            }
            file.delete()
        }
    }

    fun renameTo(source: File, target: File) {
        if (!source.exists()) {
            return
        }

        if (source.isFile) {
            source.renameTo(target)
        } else {
            target.mkdir()
            val fileArray = source.listFiles() ?: return
            for (file in fileArray) {
                val renamedFile = File(target, file.name)
                if (file.isFile) {
                    file.renameTo(renamedFile)
                } else {
                    renameTo(file, renamedFile)
                    file.delete()
                }
            }
        }
        source.delete()
    }

    fun getFileCount(file: File): Int {
        if (!file.exists() || file.isFile) {
            return 0
        }

        var fileCount = 0
        val listFiles = file.listFiles() ?: emptyArray()
        for (currentFile in listFiles) {
            if (currentFile.isDirectory) {
                fileCount += getFileCount(currentFile)
            }
        }
        fileCount += listFiles.size
        return fileCount
    }

    fun getTotalBytes(file: File): Long {
        if (!file.exists()) {
            return 0L
        }

        return if (file.isFile) {
            file.length()
        } else {
            var totalBytes = 0L
            val listFiles = file.listFiles() ?: emptyArray()
            for (currentFile in listFiles) {
                totalBytes += if (currentFile.isFile) {
                    currentFile.length()
                } else {
                    getTotalBytes(currentFile)
                }
            }
            totalBytes
        }
    }

    /**
     * 文件大小格式化
     * @param size 单位为B、kb、mb、gb转换
     * @return
     */
    fun formatBytes(size: Long): String {
        val kb = 1024L
        val mb = kb * 1024
        val gb = mb * 1024

        return if (size >= gb) {
            div(size.toString(), gb.toString()) + "GB"
        } else if (size >= mb) {
            div(size.toString(), mb.toString()) + "MB"
        } else if (size > kb) {
            div(size.toString(), kb.toString()) + "KB"
        } else {
            div(size.toString(), "1") + "B"
        }
    }

    private fun div(v1: String?, v2: String?): String {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        val divide: BigDecimal = b1.divide(b2, 2, BigDecimal.ROUND_HALF_DOWN)
        return divide.toString()
    }

    suspend fun extraZipInputStream(
        outPath: String,
        inputStream: ZipInputStream,
        progress: suspend (fileName: String) -> Unit
    ) {
        return withContext(Dispatchers.IO) {
            //读取一个目录
            var nextEntry: LocalFileHeader? = inputStream.nextEntry
            val inputChannel = Channels.newChannel(inputStream)
            inputStream.use {
                inputChannel.use {
                    val buffer = ByteBuffer.allocate(4096)
                    //不为空进入循环
                    while (nextEntry != null) {
                        val name: String = nextEntry!!.fileName
                        val file = File("$outPath/$name")
                        progress(name)
                        //如果是目录，创建目录
                        if (nextEntry!!.isDirectory) {
                            file.mkdir()
                        } else {
                            //文件则写入具体的路径中
                            val fileOutputStream = FileOutputStream(file)
                            val outputChannel = fileOutputStream.channel
                            fileOutputStream.use {
                                outputChannel.use {
                                    while (inputChannel.read(buffer) != -1) {
                                        buffer.flip()
                                        outputChannel.write(buffer)
                                        buffer.clear()
                                    }
                                }
                            }
                        }
                        //读取下一个目录，作为循环条件
                        nextEntry = inputStream.nextEntry
                    }
                }
            }
        }
    }

}