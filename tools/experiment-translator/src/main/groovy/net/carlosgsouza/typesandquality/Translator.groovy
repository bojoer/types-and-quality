package net.carlosgsouza.typesandquality

import groovy.io.FileType;


class Translator {
	
	def dictionary

	public Translator(dictionary) {
		this.dictionary = dictionary + dictionary.collectEntries{ [firstCharToUpper(it.key), firstCharToUpper(it.value)] } 
	}
		
	def firstCharToUpper(value) {
		value.replaceFirst(value[0], value[0].toUpperCase())
	}
	
	def translateText(text) {
		def result = text
		dictionary.each {
			result = result.replace(it.key, it.value)
		}
		return result
	}
	
	def translateFiles(File sourceFolder, File destinationFolder) {
		copyFolder(sourceFolder, destinationFolder)
		
		destinationFolder.eachFileRecurse(FileType.FILES) {
			def translatedName = translateText(it.absolutePath)
			
			if(translatedName == it.absolutePath) {
				return
			}
			def success = it.renameTo(translatedName)
			
			println "$success\t$it.name --> $translatedName"
		}
	}
	
	def copyFolder(File sourceFolder, File destinationFolder) {
		new AntBuilder().copy(todir:destinationFolder.absolutePath) {
			fileset(dir: sourceFolder.absolutePath)
		}
	}

}
