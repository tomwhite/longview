/*
Copyright 2010 Tom White

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

Date.prototype.getDayNumber = function() {
  var jan1 = new Date(this.getFullYear(), 0, 1);
  return Math.ceil((this - jan1) / 86400000);
} 

function EasterDateRange(fromGregorianYear, toGregorianYear) {
  this.fromGregorianYear = fromGregorianYear;
  this.toGregorianYear = toGregorianYear;
}

function EasterIterator(range) {
  this.range = range;
  this.gregorianYear = range.fromGregorianYear;
}

EasterIterator.prototype.next = function(){  
  if (this.gregorianYear >= this.range.toGregorianYear)  
    throw StopIteration;  
  else
    return easter(this.gregorianYear++); 
};

EasterDateRange.prototype.__iterator__ = function(){  
  return new EasterIterator(this);  
};  

function easter(gregorianYear) {
  // Thomas H. O'Beirne's method (1956) as reproduced by Ian Stewart
  // (Scientific American, March 2001)
  
  var a = gregorianYear % 19;
  var b = gregorianYear / 100 | 0;
  var c = gregorianYear % 100;
  var d = b / 4 | 0;
  var e = b % 4;
  
  var g = (8 * b + 13) / 25 | 0;
  var h = (19 * a + b - d - g + 15) % 30;

  var m = (a + 11 * h) / 319 | 0;

  var j = c / 4 | 0;
  var k = c % 4;

  var l = (2 * e + 2 * j - k - h + m + 32) % 7;
  var n = (h - m + l + 90) / 25 | 0;
  var p = (h - m + l + n + 19) % 32;
  
  return new Date(gregorianYear, n - 1, p);
}
