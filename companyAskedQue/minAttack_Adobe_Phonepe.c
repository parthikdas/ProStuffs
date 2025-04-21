/*
	We are playing a video game and in that we have to defeat the enemy who has H health and we can use 2 types of 
	attack - Special attack (can only be used once) that can do Y health damage and normal attack that can do X 
	health damage. you will win if enemy health <= 0. Find the minimum number of attack?
*/
#include <stdio.h>
int main() {
	int h, y, x;
	scanf("%d %d %d", &h, &y, &x);
	if(y >= h) { // if special power is greater than total health of villian then only 1
		printf("We only need 1 attack");
	} else {
		int answer = ceil((h - y) / (x * 1.0)) + 1; // reduce the total heath with special attack then divide it by normal
		printf("We need %d no of attacks to defeat him", answer);
	}
}