import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        checkDuplicatedEntries(points);
        int N = points.length;
        Arrays.sort(points);

        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        for (int i = 0; i<N; i++) {
            for (int j = i + 1; j<N; j++) {
                for (int k = j + 1; k<N; k++) {
                    for (int l = k + 1; l<N; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                            lineSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }

        segments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(segments);

    }    // finds all line segments containing 4 points
    public int numberOfSegments() {
        return segments.length;
    }       // the number of line segments
    public LineSegment[] segments() {
        return segments;
    }                // the line segments

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }

}