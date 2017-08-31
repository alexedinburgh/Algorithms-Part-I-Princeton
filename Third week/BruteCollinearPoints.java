import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segs;
    private final Point[] pts;

    public BruteCollinearPoints(Point[] points) {
        checkPoint(points);

        pts = new Point[points.length];
        System.arraycopy(points, 0, pts, 0, pts.length);
        Arrays.sort(pts);
        checkDuplicity(pts);

        segs = new ArrayList<LineSegment>();
        findCollinear();
    }

    /**
     * Find collinear segments
     */
    private void findCollinear() {
        for (int pp = 0; pp < pts.length; pp++) {
            for (int qq = pp + 1; qq < pts.length; qq++) {
                for (int rr = qq + 1; rr < pts.length; rr++) {
                    for (int ss = rr + 1; ss < pts.length; ss++) {
                        Point p = pts[pp];
                        Point q = pts[qq];
                        Point r = pts[rr];
                        Point s = pts[ss];

                        if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)) {
                            segs.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    /**
     * Check points duplicity
     *
     * @param sortedPts points to be verified
     * @throws IllegalArgumentException if points have duplicity
     */
    private void checkDuplicity(Point[] sortedPts) {
        for (int i = 1; i < sortedPts.length; i++) {
            if (sortedPts[i - 1].compareTo(sortedPts[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Check argument points is valid
     *
     * @param points inputs points
     * @throws IllegalArgumentException if not valid inputs
     */
    private void checkPoint(Point[] points) {
        // Check null inputs
        if (points == null) {
            throw new IllegalArgumentException();
        }

        // Check null point
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

    }

    /**
     * Get number of collinear segments
     *
     * @return number of segments
     */
    public int numberOfSegments() {
        return segs.size();
    }

    /**
     * Get the array of collinear segments
     *
     * @return collinear segments
     */
    public LineSegment[] segments() {
        return segs.toArray(new LineSegment[segs.size()]);
    }

    public static void main(String[] args) {
        // No test
    }
}