import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segs;
    private final Point[] pts;

    public FastCollinearPoints(Point[] points) {
        checkPoint(points);

        pts = new Point[points.length];
        System.arraycopy(points, 0, pts, 0, pts.length);
        Arrays.sort(pts);
        checkDuplicity(pts);

        segs = new ArrayList<>();
        findCollinear();
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

    private void findCollinear() {
        // less than 4 point no collinear
        if (pts.length < 4) {
            return;
        }

        Point[] slopeOrderPts = new Point[pts.length];

        for (int i = 0; i < pts.length; i++) {
            Point org = pts[i];
            System.arraycopy(pts, 0, slopeOrderPts, 0, pts.length);
            Arrays.sort(slopeOrderPts, org.slopeOrder());

            Point ptStart = slopeOrderPts[0];
            double prevSlope = org.slopeTo(ptStart);
            int nPtInGroup = 1;
            for (int pos = 1; pos < pts.length; pos++) {
                Point ptEnd = slopeOrderPts[pos];
                double curSlope = org.slopeTo(ptEnd);

                if (prevSlope == curSlope) {
                    ++nPtInGroup;
                }

                if ((prevSlope != curSlope) ||
                        (pos == (pts.length - 1))) {
                    if (nPtInGroup >= 3 && ptStart.compareTo(org) > 0) {
                        // special case for end of array
                        if (pos == (pts.length - 1) && prevSlope == curSlope) {
                            segs.add(new LineSegment(org, ptEnd));
                        } else {
                            segs.add(new LineSegment(org, slopeOrderPts[pos - 1]));
                        }
                    }
                    ptStart = slopeOrderPts[pos];
                    prevSlope = curSlope;
                    nPtInGroup = 1;
                }
            }
        }
    }

    public int numberOfSegments() {
        return segs.size();
    }

    public LineSegment[] segments() {
        return segs.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // No test
    }
}