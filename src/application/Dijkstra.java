/*package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	static ArrayList<Capital> Countrys = new ArrayList<Capital>();
	static HashMap<String, Capital> allNodes = new HashMap<>();
	private Capital source;
	private Capital destination;
	private PriorityQueue<Capital> heap;

	public Dijkstra() {
	}

	public Dijkstra(ArrayList<Capital> Countrys, Capital source, Capital destination) {
		heap = new PriorityQueue<>(Countrys.size());
		this.destination = destination;
		this.Countrys = Countrys;
		for (Capital country : Countrys) {
			country.resetTemps();
			if (country == source) {
				country.setTempCost(0);
			}
			heap.add(country);
		}
	}

	public void generateDijkstra() {
		while (!heap.isEmpty()) {
			Capital minUnknown = heap.poll(); 
			LinkedList<Edge> adjacentsList = minUnknown.getAdjacentsList(); 
			for (Edge edge : adjacentsList) { 
				Capital adjacentCity = edge.getAdjacentCity();
					if ((minUnknown.getTempCost() + edge.getDistance()) < adjacentCity.getTempCost()) {
						heap.remove(adjacentCity);
						adjacentCity.setTempCost(minUnknown.getTempCost() + edge.getDistance());
						adjacentCity.setTempPrev(minUnknown);
						heap.add(adjacentCity);
					}
				
			}
		}
	}

	private String pathString;
	 String distanceString;

	public Capital[] pathTo(Capital destination) {
		LinkedList<Capital> countries = new LinkedList<>();
		Capital iterator = destination;
		distanceString = String.format("%.2f", destination.getTempCost());
		while (iterator != source) {
			countries.addFirst(iterator);
			pathString = iterator.getFullName() + " : " + String.format("%.2f", iterator.getTempCost()) + "  KM" + "\n"
					  + "\t\t,  "+ pathString;
			iterator = iterator.getTempPrev();
		}

		return countries.toArray(new Capital[0]);
	}

	public String getPathString() {
		if (countOccurrences(pathString, '\n') <= 1) {
			pathString = "No Path \n 0 ";
			distanceString = ("0") ;
			return pathString;
		}

		pathString = "\t" + pathString;

     	int truncateIndex = pathString.lastIndexOf('\n');
     	pathString = pathString.substring(0, truncateIndex);

		return pathString;
	}

	private static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

	public static ArrayList<Capital> readFile() throws FileNotFoundException {
		String line = null;
		int numberOfCountryes, numberOfEdges;
		File file = new File("data.txt.txt");
		Scanner scan = new Scanner(file);
		line = scan.nextLine();
		String[] str = line.split(" ");
		numberOfCountryes = Integer.parseInt(str[0]);
		numberOfEdges = Integer.parseInt(str[1]);
		
		for (int i = 0; i < numberOfCountryes; i++) {
			float x, y;
			line = scan.nextLine();
			String[] strN = line.split(" ");
			x = (float) Double.parseDouble(strN[1]);
			y = (float) Double.parseDouble(strN[2]);
			Capital newCountry = new Capital(strN[0], x, y);
			Countrys.add(newCountry);
			allNodes.putIfAbsent(strN[0], newCountry);
			
		}
		for (int i = 0; i < numberOfEdges; i++) {
			line = scan.nextLine();
			String[] strN = line.split(" ");
			String fromCityName = strN[0], toCityName = strN[1];
			Capital fromCity = allNodes.get(fromCityName), toCity = allNodes.get(toCityName);
			double distance = distance(fromCity.x,fromCity.y,toCity.x,toCity.y);
			fromCity.addAdjacentCity(toCity, distance); 
		}

		return Countrys;

	}		
	
	public static double distance(double x1, double y1, double x2, double y2) {
		 return 6378.8 * Math.acos((Math.sin(Math.toRadians(y1)) * Math.sin(Math.toRadians(y2)))
							+ Math.cos(Math.toRadians(y1)) * Math.cos(Math.toRadians(y2))
									* Math.cos(Math.toRadians(x1) - Math.toRadians(x2)));
		  }

     //the midel of earth 
}
*/

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    static ArrayList<Capital> Countrys = new ArrayList<Capital>();
    static HashMap<String, Capital> allNodes = new HashMap<>();
    private Capital source;
    private Capital destination;
    private PriorityQueue<Capital> heap;

    public Dijkstra() {
    }

    public Dijkstra(ArrayList<Capital> Countrys, Capital source, Capital destination) {
        this.heap = new PriorityQueue<>(new Comparator<Capital>() {
            @Override
            public int compare(Capital c1, Capital c2) {
                return Double.compare(c1.getTempCost(), c2.getTempCost());
            }
        });
        this.destination = destination;
        this.Countrys = Countrys;
        for (Capital country : Countrys) {
            country.resetTemps();
            if (country == source) {
                country.setTempCost(0);
            }
            heap.add(country);
        }
    }

    public void generateDijkstra() {
        while (!heap.isEmpty()) {
            Capital minUnknown = heap.poll(); 
            LinkedList<Edge> adjacentsList = minUnknown.getAdjacentsList(); 
            for (Edge edge : adjacentsList) { 
                Capital adjacentCity = edge.getAdjacentCity();
                if ((minUnknown.getTempCost() + edge.getDistance()) < adjacentCity.getTempCost()) {
                   heap.remove(adjacentCity);
                	
                    adjacentCity.setTempCost(minUnknown.getTempCost() + edge.getDistance());
                    adjacentCity.setTempPrev(minUnknown);
                    heap.add(adjacentCity);
                }
            }
        }
    }

    private String pathString;
    String distanceString;

    public Capital[] pathTo(Capital destination) {
        LinkedList<Capital> countries = new LinkedList<>();
        Capital iterator = destination;
        distanceString = String.format("%.2f", destination.getTempCost());
        while (iterator != source) {
            countries.addFirst(iterator);
            pathString = iterator.getFullName() + " : " + String.format("%.2f", iterator.getTempCost()) + "  KM" + "\n"
                    + "\t\t,  " + pathString;
            iterator = iterator.getTempPrev();
        }

        return countries.toArray(new Capital[0]);
    }

    public String getPathString() {
        if (countOccurrences(pathString, '\n') <= 1) {
        

            pathString = "No Path ";
            
            	
            distanceString = ("0");
            return pathString;
        }
        
        
       
        

        pathString = "\t" + pathString;

        int truncateIndex = pathString.lastIndexOf('\n');
        pathString = pathString.substring(0, truncateIndex);

        return pathString;
    }

    private static int countOccurrences(String haystack, char needle) {
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Capital> readFile() throws FileNotFoundException {
        String line = null;
        int numberOfCountryes, numberOfEdges;
        File file = new File("data.txt.txt");
        Scanner scan = new Scanner(file);
        line = scan.nextLine();
        String[] str = line.split(" ");
        numberOfCountryes = Integer.parseInt(str[0]);
        numberOfEdges = Integer.parseInt(str[1]);

        for (int i = 0; i < numberOfCountryes; i++) {
            float x, y;
            line = scan.nextLine();
            String[] strN = line.split(" ");
            x = (float) Double.parseDouble(strN[1]);
            y = (float) Double.parseDouble(strN[2]);
            Capital newCapital = new Capital(strN[0], x, y);
            Countrys.add(newCapital);
            allNodes.putIfAbsent(strN[0], newCapital);

        }
        for (int i = 0; i < numberOfEdges; i++) {
            line = scan.nextLine();
            String[] strN = line.split(" ");
            String fromCityName = strN[0], toCityName = strN[1];
            Capital fromCity = allNodes.get(fromCityName), toCity = allNodes.get(toCityName);
            double distance = distance(fromCity.x, fromCity.y, toCity.x, toCity.y);
            fromCity.addAdjacentCity(toCity, distance);
        }

        return Countrys;

    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return 6378.8 * Math.acos((Math.sin(Math.toRadians(y1)) * Math.sin(Math.toRadians(y2)))
                + Math.cos(Math.toRadians(y1)) * Math.cos(Math.toRadians(y2))
                * Math.cos(Math.toRadians(x1) - Math.toRadians(x2)));
    }

    //the midel of earth 
}

