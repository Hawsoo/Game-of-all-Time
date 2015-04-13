package us.teamgreat.isoleveleditor.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Puppet;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entities;
import us.teamgreat.isoleveleditor.engine.entity.LE_EntityTypes;
import us.teamgreat.isoleveleditor.engine.util.EditorIO;
import us.teamgreat.isoleveleditor.resources.LE_Resources;

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
	private JCheckBoxMenuItem chckbxmntmFocusJustPuppets;
	private JCheckBoxMenuItem chckbxmntmAstheticDesign;
	private JCheckBoxMenuItem chckbxmntmWallDesign;
	private JCheckBoxMenuItem chckbxmntmEventDesign;
	
	private JRadioButton rdbtnAstheticDesign;
	private JRadioButton rdbtnWallDesign;
	private JRadioButton rdbtnEventDesign;
	
	private JComboBox<LE_Entities> cmbEntityType;
	private JSlider sldVerticalRange;
	private JRadioButton rdbtnNW;
	private JRadioButton rdbtnN;
	private JRadioButton rdbtnNE;
	private JRadioButton rdbtnW;
	private JRadioButton rdbtnE;
	private JRadioButton rdbtnSE;
	private JRadioButton rdbtnS;
	private JRadioButton rdbtnSW;
	
	/**
	 * Create the frame.
	 */
	public PropertiesWindow()
	{
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(232, 290));
		setLocation(new Point(ViewerWindow.windowpos.x - 232 - 32, ViewerWindow.windowpos.y));
		addWindowListener(LE_Resources.DEFAULT_LISTENER);
		
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
					LE_Resources.showYdepth = chckbxmntmYdepth.isSelected();
				}
			});
			mnView.add(chckbxmntmYdepth);
		}
		
		chckbxmntmFocusJustPuppets = new JCheckBoxMenuItem("Focus just on puppets (ignore selection on any other object)");
		{
			chckbxmntmFocusJustPuppets.setSelected(false);
			chckbxmntmFocusJustPuppets.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					LE_Resources.focusPuppets = chckbxmntmFocusJustPuppets.isSelected();
				}
			});
			mnView.add(chckbxmntmFocusJustPuppets);
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
					LE_Resources.showAsthetics = chckbxmntmAstheticDesign.isSelected();
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
					LE_Resources.showWalls = chckbxmntmWallDesign.isSelected();
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
					LE_Resources.showEvents = chckbxmntmEventDesign.isSelected();
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
		cmbEntityType = new JComboBox<LE_Entities>();
		sl_contentPane.putConstraint(SpringLayout.WEST, cmbEntityType, 10, SpringLayout.WEST, contentPane);
		{
			// Add in entries
			EnumSet<LE_Entities> entities = EnumSet.allOf(LE_Entities.class);
			DefaultComboBoxModel<LE_Entities> entityModel = new DefaultComboBoxModel<LE_Entities>();
			for (LE_Entities entity : entities) entityModel.addElement(entity);
			cmbEntityType.setModel(entityModel);
			
			cmbEntityType.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Update variable
					LE_Resources.currentmodel = cmbEntityType.getItemAt(cmbEntityType.getSelectedIndex());
				}
			});
			
			LE_Resources.currentmodel = cmbEntityType.getItemAt(0);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, cmbEntityType, 10, SpringLayout.NORTH, contentPane);
			contentPane.add(cmbEntityType);
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
					LE_Resources.currenttype = LE_EntityTypes.ASTHETIC_ENTITY;
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
					LE_Resources.currenttype = LE_EntityTypes.WALL_ENTITY;
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
					LE_Resources.currenttype = LE_EntityTypes.EVENT_ENTITY;
				}
			});
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnEventDesign, 6, SpringLayout.SOUTH, rdbtnWallDesign);
			sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnEventDesign, 0, SpringLayout.WEST, cmbEntityType);
			designbuttons.add(rdbtnEventDesign);
			contentPane.add(rdbtnEventDesign);
		}
		
		// Vertical 'y' slider
		sldVerticalRange = new JSlider();
		sl_contentPane.putConstraint(SpringLayout.WEST, sldVerticalRange, 155, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, sldVerticalRange, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cmbEntityType, -10, SpringLayout.WEST, sldVerticalRange);
		{
			sldVerticalRange.addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e)
				{
					// Update change
					ViewerWindow.sliderchanged = true;
					LE_Resources.yVal = sldVerticalRange.getValue();
				}
			});
			
			sldVerticalRange.setOrientation(SwingConstants.VERTICAL);
			sldVerticalRange.setValue(0);
			
			LE_Resources.yVal = sldVerticalRange.getValue();
			sl_contentPane.putConstraint(SpringLayout.NORTH, sldVerticalRange, 10, SpringLayout.NORTH, contentPane);
			contentPane.add(sldVerticalRange);
		}
		
		// Set up directional properties
		JLabel lblDirection = new JLabel("Direction:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDirection, 6, SpringLayout.SOUTH, rdbtnEventDesign);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDirection, 0, SpringLayout.WEST, cmbEntityType);
		contentPane.add(lblDirection);
		
		ButtonGroup directiongroup = new ButtonGroup();
		{
			rdbtnNW = new JRadioButton("");
			{
				rdbtnNW.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_NW;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnNW, 6, SpringLayout.SOUTH, lblDirection);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnNW, 0, SpringLayout.WEST, cmbEntityType);
				directiongroup.add(rdbtnNW);
				contentPane.add(rdbtnNW);
			}
			
			rdbtnN = new JRadioButton("");
			{
				rdbtnN.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_N;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnN, 6, SpringLayout.SOUTH, lblDirection);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnN, 6, SpringLayout.EAST, rdbtnNW);
				directiongroup.add(rdbtnN);
				contentPane.add(rdbtnN);
			}
			
			rdbtnNE = new JRadioButton("");
			{
				rdbtnNE.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_NE;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnNE, 6, SpringLayout.EAST, rdbtnN);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, rdbtnNE, 0, SpringLayout.SOUTH, rdbtnNW);
				directiongroup.add(rdbtnNE);
				contentPane.add(rdbtnNE);
			}
			
			rdbtnW = new JRadioButton("");
			{
				rdbtnW.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_W;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnW, 6, SpringLayout.SOUTH, rdbtnNW);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnW, 0, SpringLayout.WEST, cmbEntityType);
				directiongroup.add(rdbtnW);
				contentPane.add(rdbtnW);
			}
			
			rdbtnE = new JRadioButton("");
			{
				rdbtnE.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_E;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnE, 6, SpringLayout.SOUTH, rdbtnNE);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnE, 0, SpringLayout.WEST, rdbtnNE);
				directiongroup.add(rdbtnE);
				contentPane.add(rdbtnE);
			}
			
			rdbtnSE = new JRadioButton("");
			{
				rdbtnSE.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_SE;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnSE, 6, SpringLayout.SOUTH, rdbtnE);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnSE, 0, SpringLayout.WEST, rdbtnNE);
				directiongroup.add(rdbtnSE);
				contentPane.add(rdbtnSE);
			}
			
			rdbtnS = new JRadioButton("");
			{
				rdbtnS.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_S;
						LE_Resources.directionChanged = true;
					}
				});
				
				rdbtnS.setSelected(true);
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnS, 0, SpringLayout.WEST, rdbtnN);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, rdbtnS, 0, SpringLayout.SOUTH, rdbtnSE);
				directiongroup.add(rdbtnS);
				contentPane.add(rdbtnS);
			}
			
			rdbtnSW = new JRadioButton("");
			{
				rdbtnSW.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Update Resources
						LE_Resources.currentdirection = Puppet.DIR_SW;
						LE_Resources.directionChanged = true;
					}
				});
				
				sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnSW, 0, SpringLayout.WEST, cmbEntityType);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, rdbtnSW, 0, SpringLayout.SOUTH, rdbtnSE);
				directiongroup.add(rdbtnSW);
				contentPane.add(rdbtnSW);
			}
		}
	}
}
