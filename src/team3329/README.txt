NOTES ON 2012Season CODE

ON UNITS OF MEASURE:

LENGTH
--------
the standard unit for length in this code is inches. Therefore
    to represent feet you must convert to inches. There are no classes for this
    conversion as that it will take to long.

TIME
-------
the standard unit for time is seconds unless otherwise stated. The metric system
    is used for the conversion of time (i.e.: s --> ms )

VECTORS FOR DRIVE CODE
----------------------
To keep numbers small and the accuracy of the robot in positioning, the vectors
used to set the PID controllers for the drive code are NOT position coordinates.
Instead they are "delta" (as in "change in") coordinates. This will tell the
robot not to "go to this position" but instead "go to this angle and distance
that is relavant to the coordinate before it". 
