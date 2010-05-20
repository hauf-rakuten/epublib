package nl.siegmann.epublib.epub;

import org.apache.log4j.Logger;

/**
 * Functionality shared by the PackageDocumentReader and the PackageDocumentWriter
 *  
 * @author paul
 *
 */
public class PackageDocumentBase {
	public static final String BOOK_ID_ID = "BookId";
	public static final String NAMESPACE_OPF = "http://www.idpf.org/2007/opf";
	public static final String NAMESPACE_DUBLIN_CORE = "http://purl.org/dc/elements/1.1/";
	public static final String PREFIX_DUBLIN_CORE = "dc";
	public static final String dateFormat = "yyyy-MM-dd";
	
	protected interface DCTags {
		String title = "title";
        String creator = "creator";
        String subject = "subject";
        String description = "description";
        String publisher = "publisher";
        String contributor = "contributor";
        String date = "date";
        String type = "type";
        String format = "format";
        String identifier = "identifier";
        String source = "source";
        String language = "language";
        String relation = "relation";
        String coverage = "coverage";
        String rights = "rights";
	}
	
	protected interface DCAttributes {
		String scheme = "scheme";
	}
	
	protected interface OPFTags {
		String metadata = "metadata";
		String meta = "meta";
		String manifest = "manifest";
		String packageTag = "package";
		String itemref = "itemref";
	}
	
	protected interface OPFAttributes {
		String uniqueIdentifier = "unique-identifier";
		String idref = "idref";
		String name = "name";
		String content = "content";
	}
}