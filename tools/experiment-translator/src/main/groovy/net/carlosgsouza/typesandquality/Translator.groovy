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
	
	def translateProject(File sourceFolder, File destinationFolder) {
		if(destinationFolder.exists()) {
			destinationFolder.deleteDir()
		}
		
		translateFolder(sourceFolder, destinationFolder)
	}
	
	def translateFolder(File sourceFolder, File destinationFolder) {
		destinationFolder.mkdirs()
		
		sourceFolder.eachDir { File file ->
			translateFolder(new File(sourceFolder, file.name), new File(destinationFolder, translateText(file.name)))
		}
		
		sourceFolder.eachFile(FileType.FILES) { sourceFile ->
			new File(destinationFolder, translateText(sourceFile.name)) << translateText(sourceFile.text)
		}
	}

}
