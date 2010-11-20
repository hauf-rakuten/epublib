package nl.siegmann.epublib.viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import nl.siegmann.epublib.browsersupport.Navigator;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetadataPane extends JPanel {

	private static final Logger log = LoggerFactory.getLogger(MetadataPane.class);
	
	private static final long serialVersionUID = -2810193923996466948L;

	public MetadataPane(Navigator navigator) {
		super(new GridLayout(0, 1));
		JTable table = new JTable(
				createTableData(navigator.getBook().getMetadata()),
				new String[] {"", ""});
        table.setFillsViewportHeight(true);
        JPanel contentPanel = new JPanel(new BorderLayout(0, 10));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        contentPanel.add(table, BorderLayout.CENTER);
        setCoverImage(contentPanel, navigator);
		this.add(scrollPane);
	}

	private void setCoverImage(JPanel contentPanel, Navigator navigator) {
		Resource coverImageResource = navigator.getBook().getCoverImage();
		if (coverImageResource == null) {
			return;
		}
		try {
			Image image = ImageIO.read(coverImageResource.getInputStream());
			image = image.getScaledInstance(200, -1, Image.SCALE_SMOOTH);
			JLabel label = new JLabel(new ImageIcon(image));
			label.setSize(100, 100);
			contentPanel.add(label, BorderLayout.NORTH);
		} catch (IOException e) {
			log.error("Unable to load cover image from book", e.getMessage());
		}
	}
	
	private Object[][] createTableData(Metadata metadata) {
		List<String[]> result = new ArrayList<String[]>();
		addStrings(metadata.getIdentifiers(), "Identifier", result);
		addStrings(metadata.getTitles(), "Title", result);
		addStrings(metadata.getAuthors(), "Author", result);
		result.add(new String[] {"Language", metadata.getLanguage()});
		addStrings(metadata.getContributors(), "Contributor", result);
		addStrings(metadata.getDescriptions(), "Description", result);
		addStrings(metadata.getPublishers(), "Publisher", result);
		addStrings(metadata.getDates(), "Date", result);
		addStrings(metadata.getSubjects(), "Subject", result);
		addStrings(metadata.getTypes(), "Type", result);
		addStrings(metadata.getRights(), "Rights", result);
		result.add(new String[] {"Format", metadata.getFormat()});
		return result.toArray(new Object[result.size()][2]);
	}

	private void addStrings(List<? extends Object> values, String label, List<String[]> result) {
		boolean labelWritten = false;
		for (int i = 0; i < values.size(); i++) {
			Object value = values.get(i);
			if (value == null) {
				continue;
			}
			String valueString = String.valueOf(value);
			if (StringUtils.isBlank(valueString))  {
				continue;
			}
			
			String currentLabel = "";
			if (! labelWritten) {
				currentLabel = label;
				labelWritten = true;
			}
			result.add(new String[] {currentLabel, valueString});
		}

	}
	
	private TableModel createTableModel(Navigator navigator) {
		return new AbstractTableModel() {
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getColumnCount() {
				return 2;
			}
		};
	}
}
