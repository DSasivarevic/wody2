#!/usr/bin/env python
""" generated source for module FFT """
# 
#  *  Copyright 2006-2007 Columbia University.
#  *
#  *  This file is part of MEAPsoft.
#  *
#  *  MEAPsoft is free software; you can redistribute it and/or modify
#  *  it under the terms of the GNU General Public License version 2 as
#  *  published by the Free Software Foundation.
#  *
#  *  MEAPsoft is distributed in the hope that it will be useful, but
#  *  WITHOUT ANY WARRANTY; without even the implied warranty of
#  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
#  *  General Public License for more details.
#  *
#  *  You should have received a copy of the GNU General Public License
#  *  along with MEAPsoft; if not, write to the Free Software
#  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
#  *  02110-1301 USA
#  *
#  *  See the file "COPYING" for the text of the license.
#  
# package: controllers
# 
#  * Utility class to perform a fast fourier transform without allocating any
#  * extra memory.
#  * 
#  * @author Mike Mandel (mim@ee.columbia.edu)
#  
# I removed some unused variables to get rid of warnings
# Xiaochao
class FFT(object):
    """ generated source for class FFT """
    n = int()
    m = int()

    #  Lookup tables. Only need to recompute when size of FFT changes.
    cos = []
    sin = []
    window = []

    def __init__(self, n):
        """ generated source for method __init__ """
        self.n = n
        self.m = ((Math.log(n) / Math.log(2)))
        #  Make sure n is a power of 2
        if n != (1 << m):
            raise RuntimeException("FFT length must be power of 2")
        #  precompute tables
        self.cos = [None]*n / 2
        self.sin = [None]*n / 2
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        i = 0
        while i < n / 2:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            self.cos[i] = Math.cos(-2 * Math.PI * i / n)
            self.sin[i] = Math.sin(-2 * Math.PI * i / n)
            i += 1
        makeWindow()

    def makeWindow(self):
        """ generated source for method makeWindow """
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        self.window = [None]*n
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        i = 0
        while i < window.length:
            i += 1

    def getWindow(self):
        """ generated source for method getWindow """
        return self.window

    # 
    # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
    # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
    # 	 * 
    # 	 * fft: in-place radix-2 DIT DFT of a complex input
    # 	 * 
    # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
    # 	 * x: double array of length n with real part of data y: double array of
    # 	 * length n with imag part of data
    # 	 * 
    # 	 * Permission to copy and use this program is granted as long as this header
    # 	 * is included.
    # 	 
    def fft(self, x, y):
        """ generated source for method fft """
        i = int()
        j = int()
        k = int()
        n1 = int()
        n2 = int()
        a = int()
        c = float()
        s = float()
        t1 = float()
        t2 = float()
        #  Bit-reverse
        j = 0
        n2 = n / 2
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        while i < n - 1:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            n1 = n2
            while j >= n1:
                j = j - n1
                n1 = n1 / 2
            j = j + n1
            if i < j:
                t1 = x[i]
                x[i] = x[j]
                x[j] = t1
                t1 = y[i]
                y[i] = y[j]
                y[j] = t1
            i += 1
        #  FFT
        n1 = 0
        n2 = 1
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        while i < m:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            #  FFT
            n1 = n2
            n2 = n2 + n2
            a = 0
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            #  FFT
            while j < n1:
                # 
                #  *  Copyright 2006-2007 Columbia University.
                #  *
                #  *  This file is part of MEAPsoft.
                #  *
                #  *  MEAPsoft is free software; you can redistribute it and/or modify
                #  *  it under the terms of the GNU General Public License version 2 as
                #  *  published by the Free Software Foundation.
                #  *
                #  *  MEAPsoft is distributed in the hope that it will be useful, but
                #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
                #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
                #  *  General Public License for more details.
                #  *
                #  *  You should have received a copy of the GNU General Public License
                #  *  along with MEAPsoft; if not, write to the Free Software
                #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
                #  *  02110-1301 USA
                #  *
                #  *  See the file "COPYING" for the text of the license.
                #  
                # 
                #  * Utility class to perform a fast fourier transform without allocating any
                #  * extra memory.
                #  * 
                #  * @author Mike Mandel (mim@ee.columbia.edu)
                #  
                # I removed some unused variables to get rid of warnings
                # Xiaochao
                #  Lookup tables. Only need to recompute when size of FFT changes.
                #  Make sure n is a power of 2
                #  precompute tables
                #  for(int i=0; i<n/4; i++) {
                #  cos[i] = Math.cos(-2*Math.PI*i/n);
                #  sin[n/4-i] = cos[i];
                #  cos[n/2-i] = -cos[i];
                #  sin[n/4+i] = cos[i];
                #  cos[n/2+i] = -cos[i];
                #  sin[n*3/4-i] = -cos[i];
                #  cos[n-i] = cos[i];
                #  sin[n*3/4+i] = -cos[i];
                #  }
                #  Make a blackman window:
                #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
                # 
                # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
                # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
                # 	 * 
                # 	 * fft: in-place radix-2 DIT DFT of a complex input
                # 	 * 
                # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
                # 	 * x: double array of length n with real part of data y: double array of
                # 	 * length n with imag part of data
                # 	 * 
                # 	 * Permission to copy and use this program is granted as long as this header
                # 	 * is included.
                # 	 
                #  Bit-reverse
                #  FFT
                c = cos[a]
                s = sin[a]
                a += 1 << (m - i - 1)
                # 
                #  *  Copyright 2006-2007 Columbia University.
                #  *
                #  *  This file is part of MEAPsoft.
                #  *
                #  *  MEAPsoft is free software; you can redistribute it and/or modify
                #  *  it under the terms of the GNU General Public License version 2 as
                #  *  published by the Free Software Foundation.
                #  *
                #  *  MEAPsoft is distributed in the hope that it will be useful, but
                #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
                #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
                #  *  General Public License for more details.
                #  *
                #  *  You should have received a copy of the GNU General Public License
                #  *  along with MEAPsoft; if not, write to the Free Software
                #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
                #  *  02110-1301 USA
                #  *
                #  *  See the file "COPYING" for the text of the license.
                #  
                # 
                #  * Utility class to perform a fast fourier transform without allocating any
                #  * extra memory.
                #  * 
                #  * @author Mike Mandel (mim@ee.columbia.edu)
                #  
                # I removed some unused variables to get rid of warnings
                # Xiaochao
                #  Lookup tables. Only need to recompute when size of FFT changes.
                #  Make sure n is a power of 2
                #  precompute tables
                #  for(int i=0; i<n/4; i++) {
                #  cos[i] = Math.cos(-2*Math.PI*i/n);
                #  sin[n/4-i] = cos[i];
                #  cos[n/2-i] = -cos[i];
                #  sin[n/4+i] = cos[i];
                #  cos[n/2+i] = -cos[i];
                #  sin[n*3/4-i] = -cos[i];
                #  cos[n-i] = cos[i];
                #  sin[n*3/4+i] = -cos[i];
                #  }
                #  Make a blackman window:
                #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
                # 
                # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
                # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
                # 	 * 
                # 	 * fft: in-place radix-2 DIT DFT of a complex input
                # 	 * 
                # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
                # 	 * x: double array of length n with real part of data y: double array of
                # 	 * length n with imag part of data
                # 	 * 
                # 	 * Permission to copy and use this program is granted as long as this header
                # 	 * is included.
                # 	 
                #  Bit-reverse
                #  FFT
                while k < n:
                    # 
                    #  *  Copyright 2006-2007 Columbia University.
                    #  *
                    #  *  This file is part of MEAPsoft.
                    #  *
                    #  *  MEAPsoft is free software; you can redistribute it and/or modify
                    #  *  it under the terms of the GNU General Public License version 2 as
                    #  *  published by the Free Software Foundation.
                    #  *
                    #  *  MEAPsoft is distributed in the hope that it will be useful, but
                    #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
                    #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
                    #  *  General Public License for more details.
                    #  *
                    #  *  You should have received a copy of the GNU General Public License
                    #  *  along with MEAPsoft; if not, write to the Free Software
                    #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
                    #  *  02110-1301 USA
                    #  *
                    #  *  See the file "COPYING" for the text of the license.
                    #  
                    # 
                    #  * Utility class to perform a fast fourier transform without allocating any
                    #  * extra memory.
                    #  * 
                    #  * @author Mike Mandel (mim@ee.columbia.edu)
                    #  
                    # I removed some unused variables to get rid of warnings
                    # Xiaochao
                    #  Lookup tables. Only need to recompute when size of FFT changes.
                    #  Make sure n is a power of 2
                    #  precompute tables
                    #  for(int i=0; i<n/4; i++) {
                    #  cos[i] = Math.cos(-2*Math.PI*i/n);
                    #  sin[n/4-i] = cos[i];
                    #  cos[n/2-i] = -cos[i];
                    #  sin[n/4+i] = cos[i];
                    #  cos[n/2+i] = -cos[i];
                    #  sin[n*3/4-i] = -cos[i];
                    #  cos[n-i] = cos[i];
                    #  sin[n*3/4+i] = -cos[i];
                    #  }
                    #  Make a blackman window:
                    #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
                    # 
                    # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
                    # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
                    # 	 * 
                    # 	 * fft: in-place radix-2 DIT DFT of a complex input
                    # 	 * 
                    # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
                    # 	 * x: double array of length n with real part of data y: double array of
                    # 	 * length n with imag part of data
                    # 	 * 
                    # 	 * Permission to copy and use this program is granted as long as this header
                    # 	 * is included.
                    # 	 
                    #  Bit-reverse
                    #  FFT
                    t1 = c * x[k + n1] - s * y[k + n1]
                    t2 = s * x[k + n1] + c * y[k + n1]
                    x[k + n1] = x[k] - t1
                    y[k + n1] = y[k] - t2
                    x[k] = x[k] + t1
                    y[k] = y[k] + t2
                    k = k + n2
                j += 1
            i += 1

    #  Test the FFT to make sure it's working
    @classmethod
    def main(cls, args):
        """ generated source for method main """
        N = 8
        fft = FFT(N)
        re = [None]*N
        im = [None]*N
        #  Impulse
        re[0] = 1
        im[0] = 0
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        i = 1
        while i < N:
            i += 1
        beforeAfter(fft, re, im)
        #  Nyquist
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        i = 0
        while i < N:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            #  FFT
            #  Test the FFT to make sure it's working
            #  Impulse
            #  Nyquist
            re[i] = Math.pow(-1, i)
            im[i] = 0
            i += 1
        beforeAfter(fft, re, im)
        #  Single sin
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        #  Single sin
        i = 0
        while i < N:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            #  FFT
            #  Test the FFT to make sure it's working
            #  Impulse
            #  Nyquist
            #  Single sin
            re[i] = Math.cos(2 * Math.PI * i / N)
            im[i] = 0
            i += 1
        beforeAfter(fft, re, im)
        #  Ramp
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        #  Single sin
        #  Ramp
        i = 0
        while i < N:
            # 
            #  *  Copyright 2006-2007 Columbia University.
            #  *
            #  *  This file is part of MEAPsoft.
            #  *
            #  *  MEAPsoft is free software; you can redistribute it and/or modify
            #  *  it under the terms of the GNU General Public License version 2 as
            #  *  published by the Free Software Foundation.
            #  *
            #  *  MEAPsoft is distributed in the hope that it will be useful, but
            #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
            #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
            #  *  General Public License for more details.
            #  *
            #  *  You should have received a copy of the GNU General Public License
            #  *  along with MEAPsoft; if not, write to the Free Software
            #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
            #  *  02110-1301 USA
            #  *
            #  *  See the file "COPYING" for the text of the license.
            #  
            # 
            #  * Utility class to perform a fast fourier transform without allocating any
            #  * extra memory.
            #  * 
            #  * @author Mike Mandel (mim@ee.columbia.edu)
            #  
            # I removed some unused variables to get rid of warnings
            # Xiaochao
            #  Lookup tables. Only need to recompute when size of FFT changes.
            #  Make sure n is a power of 2
            #  precompute tables
            #  for(int i=0; i<n/4; i++) {
            #  cos[i] = Math.cos(-2*Math.PI*i/n);
            #  sin[n/4-i] = cos[i];
            #  cos[n/2-i] = -cos[i];
            #  sin[n/4+i] = cos[i];
            #  cos[n/2+i] = -cos[i];
            #  sin[n*3/4-i] = -cos[i];
            #  cos[n-i] = cos[i];
            #  sin[n*3/4+i] = -cos[i];
            #  }
            #  Make a blackman window:
            #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
            # 
            # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
            # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
            # 	 * 
            # 	 * fft: in-place radix-2 DIT DFT of a complex input
            # 	 * 
            # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
            # 	 * x: double array of length n with real part of data y: double array of
            # 	 * length n with imag part of data
            # 	 * 
            # 	 * Permission to copy and use this program is granted as long as this header
            # 	 * is included.
            # 	 
            #  Bit-reverse
            #  FFT
            #  Test the FFT to make sure it's working
            #  Impulse
            #  Nyquist
            #  Single sin
            #  Ramp
            re[i] = i
            im[i] = 0
            i += 1
        beforeAfter(fft, re, im)
        time = System.currentTimeMillis()
        iter = 30000
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        #  Single sin
        #  Ramp
        i = 0
        while i < iter:
            i += 1
        time = System.currentTimeMillis() - time
        print "Averaged " + (time / iter) + "ms per iteration"

    @classmethod
    def beforeAfter(cls, fft, re, im):
        """ generated source for method beforeAfter """
        print "Before: "
        printReIm(re, im)
        fft.fft(re, im)
        print "After: "
        printReIm(re, im)

    @classmethod
    def printReIm(cls, re, im):
        """ generated source for method printReIm """
        print "Re: [",
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        #  Single sin
        #  Ramp
        i = 0
        while i < re.length:
            i += 1
        print "]\nIm: [",
        # 
        #  *  Copyright 2006-2007 Columbia University.
        #  *
        #  *  This file is part of MEAPsoft.
        #  *
        #  *  MEAPsoft is free software; you can redistribute it and/or modify
        #  *  it under the terms of the GNU General Public License version 2 as
        #  *  published by the Free Software Foundation.
        #  *
        #  *  MEAPsoft is distributed in the hope that it will be useful, but
        #  *  WITHOUT ANY WARRANTY; without even the implied warranty of
        #  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        #  *  General Public License for more details.
        #  *
        #  *  You should have received a copy of the GNU General Public License
        #  *  along with MEAPsoft; if not, write to the Free Software
        #  *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
        #  *  02110-1301 USA
        #  *
        #  *  See the file "COPYING" for the text of the license.
        #  
        # 
        #  * Utility class to perform a fast fourier transform without allocating any
        #  * extra memory.
        #  * 
        #  * @author Mike Mandel (mim@ee.columbia.edu)
        #  
        # I removed some unused variables to get rid of warnings
        # Xiaochao
        #  Lookup tables. Only need to recompute when size of FFT changes.
        #  Make sure n is a power of 2
        #  precompute tables
        #  for(int i=0; i<n/4; i++) {
        #  cos[i] = Math.cos(-2*Math.PI*i/n);
        #  sin[n/4-i] = cos[i];
        #  cos[n/2-i] = -cos[i];
        #  sin[n/4+i] = cos[i];
        #  cos[n/2+i] = -cos[i];
        #  sin[n*3/4-i] = -cos[i];
        #  cos[n-i] = cos[i];
        #  sin[n*3/4+i] = -cos[i];
        #  }
        #  Make a blackman window:
        #  w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        # 
        # 	 * fft.c Douglas L. Jones University of Illinois at Urbana-Champaign January
        # 	 * 19, 1992 http://cnx.rice.edu/content/m12016/latest/
        # 	 * 
        # 	 * fft: in-place radix-2 DIT DFT of a complex input
        # 	 * 
        # 	 * input: n: length of FFT: must be a power of two m: n = 2**m input/output
        # 	 * x: double array of length n with real part of data y: double array of
        # 	 * length n with imag part of data
        # 	 * 
        # 	 * Permission to copy and use this program is granted as long as this header
        # 	 * is included.
        # 	 
        #  Bit-reverse
        #  FFT
        #  Test the FFT to make sure it's working
        #  Impulse
        #  Nyquist
        #  Single sin
        #  Ramp
        i = 0
        while i < im.length:
            i += 1
        print "]"


if __name__ == '__main__':
    import sys
    FFT.main(sys.argv)

