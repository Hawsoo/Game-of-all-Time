package us.teamgreat.isoleveleditor.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import us.teamgreat.MainClass;
import us.teamgreat.isoleveleditor.engine.entity.Entities;
import us.teamgreat.isoleveleditor.engine.entity.EntityTypes;
import us.teamgreat.isoleveleditor.engine.util.EditorIO;
import us.teamgreat.isoleveleditor.resources.Resources;

/**
 * Properties window for editing entities.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class PropertiesWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JCheckBoxMenuItem chckbxmntmYdepth;
	private JCheckBoxMenuItem chckbxmntmAstheticDesign;
	private JCheckBoxMenuItem chckbxmntmWallDesign;
	private JCheckBoxMenuItem chckbxmntmEventDesign;
	
	private JRadioButton rdbtnAstheticDesign;
	private JRadioButton rdbtnWallDesign;
	private JRadioButton rdbtnEventDesign;
	
	private JComboBox<Entities> cmbEntityType;
	private JSlider sldVerticalRange;
	
	/**
	 * Create the frame.
	 */
	public PropertiesWindow()
	{
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(232, 278));
		setLocation(new Point(ViewerWindow.windowpos.x - 232 - 32, ViewerWindow.windowpos.y));
		addWindowListener(Resources.DEFAULT_LISTENER);
		
		// Setup menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// File section
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		{
			mntmNew.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Clear the field
					ViewerWindow.clearEntities();
					ViewerWindow.saveAsRequired = true;
					ViewerWindow.savePath = null;
				}
			});
			
			mnFile.add(mntmNew);
		}
		
		mnFile.addSeparator();
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		{
			mntmOpen.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Open file
					EditorIO.openFile();
				}
			});
			
			mnFile.add(mntmOpen);
		}
		
		JMenuItem mntmSave = new JMenuItem("Save");
		{
			mntmSave.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Save file
					EditorIO.saveFile(false);
				}
			});
			
			mnFile.add(mntmSave);
		}
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		{
			mntmSaveAs.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Save file (w/ forced save as..)
					EditorIO.saveFile(true);
				}
			});
			
			mnFile.add(mntmSaveAs);
		}
		
		mnFile.addSeparator();
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		{
			mntmQuit.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Exit
					MainClass.exit();
				}
			});
			mnFile.add(mntmQuit);
		}
		
		// View section
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		chckbxmntmYdepth = new JCheckBoxMenuItem("Show Y Depth");
		{
			chckbxmntmYdepth.setSelected(false);
			chckbxmntmYdepth.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					Resources.showYdepth = chckbxmntmYdepth.isSelected();
				}
			});
			mnView.add(chckbxmntmYdepth);
		}
		
		mnView.addSeparator();
		
		chckbxmntmAstheticDesign = new JCheckBoxMenuItem("Asthetic Design");
		{
			chckbxmntmAstheticDesign.setSelected(true);
			chckbxmntmAstheticDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					Resources.showAsthetics = chckbxmntmAstheticDesign.isSelected();
				}
			});
			mnView.add(chckbxmntmAstheticDesign);
		}
		
		chckbxmntmWallDesign = new JCheckBoxMenuItem("Wall Design");
		{
			chckbxmntmWallDesign.setSelected(true);
			chckbxmntmWallDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					Resources.showWalls = chckbxmntmWallDesign.isSelected();
				}
			});
			mnView.add(chckbxmntmWallDesign);
		}
		
		chckbxmntmEventDesign = new JCheckBoxMenuItem("Event Design");
		{
			chckbxmntmEventDesign.setSelected(true);
			chckbxmntmEventDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					Resources.showEvents = chckbxmntmEventDesign.isSelected();
				}
			});
			mnView.add(chckbxmntmEventDesign);
		}
		
		// Create content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		// Entity type
		cmbEntityType = new JComboBox<Entities>();
		{
			// Add in entries
			EnumSet<Entities> entities = EnumSet.allOf(Entities.class);
			DefaultComboBoxModel<Entities> entityModel = new DefaultComboBoxModel<Entities>();
			for (Entities entity : entities) entityModel.addElement(entity);
			cmbEntityType.setModel(entityModel);
			
			cmbEntityType.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					Resources.currentmodel = cmbEntityType.getItemAt(cmbEntityType.getSelectedIndex());
				}
			});
			
			Resources.currentmodel = cmbEntityType.getItemAt(0);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, cmbEntityType, 10, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, cmbEntityType, 10, SpringLayout.WEST, contentPane);
			contentPane.add(cmbEntityType);
		}
		
		// Event Description
		JButton btnSetEventDescription = new JButton("Set Event Description");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSetEventDescription, 10, SpringLayout.WEST, contentPane);
		{
			sl_contentPane.putConstraint(SpringLayout.EAST, cmbEntityType, 0, SpringLayout.EAST, btnSetEventDescription);
			contentPane.add(btnSetEventDescription);
		}
		
		// Room designs
		ButtonGroup designbuttons = new ButtonGroup();
		
		// Asthetic design
		rdbtnAstheticDesign = new JRadioButton("Asthetic Design");
		{
			rdbtnAstheticDesign.setSelected(true);
			
			rdbtnAstheticDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Set the current entity type
					ViewerWindow.layerchanged = true;
					Resources.currenttype = EntityTypes.ASTHETIC_ENTITY;
				}
			});
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnAstheticDesign, 6, SpringLayout.SOUTH, cmbEntityType);
			sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnAstheticDesign, 0, SpringLayout.WEST, cmbEntityType);
			designbuttons.add(rdbtnAstheticDesign);
			contentPane.add(rdbtnAstheticDesign);
		}
		
		// Wall design
		rdbtnWallDesign = new JRadioButton("Wall Design");
		{
			rdbtnWallDesign.setSelected(false);
			
			rdbtnWallDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Set the current entity type
					ViewerWindow.layerchanged = true;
					Resources.currenttype = EntityTypes.WALL_ENTITY;
				}
			});
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnWallDesign, 6, SpringLayout.SOUTH, rdbtnAstheticDesign);
			sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnWallDesign, 0, SpringLayout.WEST, cmbEntityType);
			designbuttons.add(rdbtnWallDesign);
			contentPane.add(rdbtnWallDesign);
		}
		
		// Event design
		rdbtnEventDesign = new JRadioButton("Event Design");
		{
			rdbtnEventDesign.setSelected(false);
			
			rdbtnEventDesign.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Set the current entity type
					ViewerWindow.layerchanged = true;
					Resources.currenttype = EntityTypes.EVENT_ENTITY;
				}
			});
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnEventDesign, 6, SpringLayout.SOUTH, rdbtnWallDesign);
			sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnEventDesign, 0, SpringLayout.WEST, cmbEntityType);
			designbuttons.add(rdbtnEventDesign);
			contentPane.add(rdbtnEventDesign);
		}
		
		// Vertical 'y' slider
		sldVerticalRange = new JSlider();
		{
			sldVerticalRange.addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e)
				{
					// Update change
					ViewerWindow.sliderchanged = true;
					Resources.yVal = sldVerticalRange.getValue();
				}
			});
			
			sldVerticalRange.setOrientation(SwingConstants.VERTICAL);
			sldVerticalRange.setValue(0);
			
			Resources.yVal = sldVerticalRange.getValue();
			
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSetEventDescription, 0, SpringLayout.SOUTH, sldVerticalRange);
			sl_contentPane.putConstraint(SpringLayout.WEST, sldVerticalRange, 10, SpringLayout.EAST, btnSetEventDescription);
			sl_contentPane.putConstraint(SpringLayout.NORTH, sldVerticalRange, 10, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, sldVerticalRange, -10, SpringLayout.EAST, contentPane);
			contentPane.add(sldVerticalRange);
		}
	}
}
