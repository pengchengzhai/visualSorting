import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JMenuBar;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;

enum SortType { BubbleSort, BasicQuickSort, InsertionSort, MergeSort, RadixLSD, RadixMSD, ShellSort, SelectionSort};

public class Visulaizer extends JFrame {

	private JPanel contentPane;
	private BarChartComponent chart; 
	private DataItem[] data;
	private int totalNumbers = 200;
	private SortType selectedSort;
	private JButton btnStart;
	
	private JCheckBoxMenuItem chkBubbleSort;
	private JCheckBoxMenuItem chkBasicQuickSort;
	private JCheckBoxMenuItem chkInsertionSort;
	private JCheckBoxMenuItem chkMergeSort;
	private JCheckBoxMenuItem chkRadixSortlsd;
	private JCheckBoxMenuItem chkRadixSortmsd;
	private JCheckBoxMenuItem chkSelectionSort;
	private JCheckBoxMenuItem chkShellSort;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visulaizer frame = new Visulaizer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void performSort(){
		Random r = new Random();
		if(selectedSort == SortType.BubbleSort || selectedSort == SortType.InsertionSort
				|| selectedSort == SortType.SelectionSort){
			totalNumbers = 200;
		}else{
			totalNumbers = 500;
		}

		data = new DataItem[totalNumbers];
		chart.setData(data);
		for(int i=0; i<totalNumbers; i++){
			data[i] = new DataItem(i+1);
		}

		// Shuffling numbers
		for(int i=totalNumbers-1; i>=1; i--){
			 int index = r.nextInt(i);
		      // Simple swap
		      DataItem a = data[index];
		      data[index] = data[i];
		      data[i] = a;
		}
		final SortArrayVisual visual = new SortArrayVisual(chart);
		Thread t = new Thread(new Runnable() {
	         public void run()
	         {
	     		switch(selectedSort){
	    		case BubbleSort:
	    			visual.bubbleSort(data, data.length);
	           	 break;
	    		case BasicQuickSort:
	           	 visual.basicQuickSort(data, data.length);
	           	 break;
	    		case InsertionSort:
	           	 visual.insertionSort(data, data.length);
	           	 break;
	    		case SelectionSort:
	    			visual.selectionSort(data, data.length);
	           	 break;
	    		case MergeSort:
	           	 visual.mergeSort(data, 0, data.length);
	           	 break;
	    		case RadixLSD:
	           	 visual.RadixSortLSD(data, data.length);
	           	 break;
	    		case RadixMSD:
		           	 visual.RadixSortMSD(data, data.length);
	           	 break;
	    		case ShellSort:
	           	 visual.shellSort(data, data.length);
	           	 break;

	    		}
				visual.verifySort(data);
	         }
	});
		t.start();
		
		

	}
	/**
	 * Create the frame.
	 */
	public Visulaizer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		
		selectedSort = SortType.BubbleSort;
		
		btnStart = new JButton("Start");
		btnStart.setBounds(150, 0, 100, 30);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSort();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnStart);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 132, 22);
		contentPane.add(menuBar);
		
		JMenu mnfile = new JMenu("Sort");
		mnfile.setMnemonic('F');
		menuBar.add(mnfile);
		
		chkBubbleSort = new JCheckBoxMenuItem("Bubble Sort");
		chkBubbleSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.BubbleSort;
				chkBubbleSort.setSelected(true);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		chkBubbleSort.setSelected(true);
		mnfile.add(chkBubbleSort);
		
		chkBasicQuickSort = new JCheckBoxMenuItem("Basic Quick Sort");
		chkBasicQuickSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.BasicQuickSort;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(true);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkBasicQuickSort);
		
		chkInsertionSort = new JCheckBoxMenuItem("Insertion Sort");
		chkInsertionSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.InsertionSort;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(true);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkInsertionSort);
		
		chkMergeSort = new JCheckBoxMenuItem("Merge Sort");
		chkMergeSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.MergeSort;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(true);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkMergeSort);
		
		chkRadixSortlsd = new JCheckBoxMenuItem("Radix Sort (LSD)");
		chkRadixSortlsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.RadixLSD;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(true);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkRadixSortlsd);
		
		chkRadixSortmsd = new JCheckBoxMenuItem("Radix Sort (MSD)");
		chkRadixSortmsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.RadixMSD;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(true);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkRadixSortmsd);
		
		chkSelectionSort = new JCheckBoxMenuItem("Selection Sort");
		chkSelectionSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.SelectionSort;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(true);
				chkShellSort.setSelected(false);
			}
		});
		mnfile.add(chkSelectionSort);
		
		chkShellSort = new JCheckBoxMenuItem("Shell Sort");
		chkShellSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSort = SortType.ShellSort;
				chkBubbleSort.setSelected(false);
				chkBasicQuickSort.setSelected(false);
				chkInsertionSort.setSelected(false);
				chkMergeSort.setSelected(false);
				chkRadixSortlsd.setSelected(false);
				chkRadixSortmsd.setSelected(false);
				chkSelectionSort.setSelected(false);
				chkShellSort.setSelected(true);
			}
		});
		mnfile.add(chkShellSort);
		
		JSeparator separator = new JSeparator();
		mnfile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		mnfile.add(mntmExit);

		data = new DataItem[totalNumbers];
		for(int i=0; i<totalNumbers; i++){
			data[i] = new DataItem(i+1);
		}

		
		chart = new BarChartComponent(data);
		chart.setBounds(0, 0, 1200, 800);
	     this.add(chart);
	}
}
