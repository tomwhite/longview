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

function drawSuperellipse(ctx, x, y, width, height, eccentricity, color) {
  var resolution = 100
  var halfWidth = width / 2.0;
  var halfHeight = height / 2.0;
  var centreX = x + halfWidth;
  var centreY = y + halfHeight;
  ctx.strokeStyle = color;
  ctx.fillStyle = color;
  ctx.lineWidth = Math.max(width, height) / 1000;
  ctx.beginPath();
  ctx.moveTo(x + width, centreY);
  for (var theta = 0.0; theta < Math.PI * 2; theta += Math.PI * 2 / resolution) {
    var sineTheta = Math.sin(theta);
    var cosineTheta = Math.cos(theta);
    r = Math.pow(1 /
          (Math.pow(Math.abs(cosineTheta) / halfWidth, eccentricity)
         + Math.pow(Math.abs(sineTheta) / halfHeight, eccentricity)), 1 / eccentricity);
    ctx.lineTo(centreX + r * cosineTheta, centreY + r * sineTheta);
  }
  ctx.fill();
  ctx.closePath();
}