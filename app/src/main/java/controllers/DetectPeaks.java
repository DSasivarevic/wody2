package controllers;

import java.util.ArrayList;
import java.util.List;


public class DetectPeaks {

    public int getRepititionCount(ArrayList<Double> data, int delta) {

        List<Peak> peaks = new DetectPeaks().peakdet(data, delta);
        int count = peaks.size();
        for (Peak peak : peaks) {
            System.out.println(peak);
        }
        return count;
    }

    List<Peak> peakdet(ArrayList<Double> vector, double triggerDelta) {
        return peakdet(vector, 0, vector.size(), triggerDelta);
    }

    /*
     !PEAKDET Detect peaks in a vector
     !
     !        call PEAKDET(MAXTAB, MINTAB, N, V, DELTA) finds the local
     !        maxima and minima ("peaks") in the vector V of size N.
     !        MAXTAB and MINTAB consists of two columns. Column 1
     !        contains indices in V, and column 2 the found values.
     !
     !        call PEAKDET(MAXTAB, MINTAB, N, V, DELTA, X) replaces the
     !        indices in MAXTAB and MINTAB with the corresponding X-values.
     !
     !        A point is considered a maximum peak if it has the maximal
     !        value, and was preceded (to the left) by a value lower by
     !        DELTA.
     !
     ! Eli Billauer, 3.4.05 (http://billauer.co.il)
     ! Translated into Fortran by Brian McNoldy (http://andrew.rsmas.miami.edu/bmcnoldy)
     ! This function is released to the public domain; Any use is allowed.*/
    List<Peak> peakdet(ArrayList<Double> vector, int offset, int length, double triggerDelta) {
        double mn = Double.POSITIVE_INFINITY;
        double mx = Double.NEGATIVE_INFINITY;
        double mnpos = Double.NaN;
        double mxpos = Double.NaN;
        int lookformax = 1;

        List<Peak> maxtab_tmp = new ArrayList<>();
        //List<Valley> mintab_tmp = new ArrayList<>();

        for (int i = offset; i < length; i++) {
            double a = vector.get(i);
            if (a > mx) {
                mx = a;
                mxpos = vector.get(i);
            }
            if (a < mn) {
                mn = a;
                mnpos = vector.get(i);
            }
            if (lookformax == 1) {
                if (a < mx - triggerDelta) {
                    maxtab_tmp.add(new Peak(mxpos, i));
                    mn = a;
                    mnpos = vector.get(i);
                    lookformax = 0;
                }
            } else {
                if (a > mn + triggerDelta) {
                    //mintab_tmp.add(new Valley(mnpos, i));
                    mx = a;
                    mxpos = vector.get(i);
                    lookformax = 1;
                }
            }
        }

        return maxtab_tmp;
    }

    static class Peak {

        public final double height;
        public final int index;

        private Peak(double height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Peak{" + "height=" + height + ", index=" + index + '}';
        }

    }

    static class Valley {

        public final double height;
        public final int index;

        private Valley(double height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Valley{" + "height=" + height + ", index=" + index + '}';
        }

    }
}