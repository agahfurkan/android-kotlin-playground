#!/bin/bash

# Generate a list of image paths tracked by Git LFS
git lfs ls-files | grep -E "\.(jpg|png)$" | awk '{print $3}' > image_paths.txt

# Loop through each image path and open in the default viewer
while IFS= read -r line; do
    open "$line"
done < image_paths.txt
