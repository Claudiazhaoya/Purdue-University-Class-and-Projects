	.arch armv6
	.eabi_attribute 27, 3
	.eabi_attribute 28, 1
	.fpu vfp
	.eabi_attribute 20, 1
	.eabi_attribute 21, 1
	.eabi_attribute 23, 3
	.eabi_attribute 24, 1
	.eabi_attribute 25, 1
	.eabi_attribute 26, 2
	.eabi_attribute 30, 6
	.eabi_attribute 18, 4
	.file	"sections.c"
	.global	a
	.data
	.align	2
	.type	a, %object
	.size	a, 4
a:
	.word	5
	.comm	buffer,4000000,4
	.section	.rodata
	.align	2
.LC0:
	.ascii	"&d=0x%x\012 &e=0x%x\012\000"
	.text
	.align	2
	.global	foo
	.type	foo, %function
foo:
	@ args = 0, pretend = 0, frame = 8
	@ frame_needed = 1, uses_anonymous_args = 0
	stmfd	sp!, {fp, lr}
	add	fp, sp, #4
	sub	sp, sp, #8
	ldr	r2, .L2
	sub	r3, fp, #8
	mov	r0, r2
	mov	r1, r3
	ldr	r2, .L2+4
	bl	printf
	mov	r0, r3
	sub	sp, fp, #4
	ldmfd	sp!, {fp, pc}
.L3:
	.align	2
.L2:
	.word	.LC0
	.word	e.2223
	.size	foo, .-foo
	.section	.rodata
	.align	2
.LC1:
	.ascii	"Hello World\012\000"
	.align	2
.LC2:
	.ascii	"&b=0x%x\012 &c=0x%x\012\000"
	.align	2
.LC3:
	.ascii	"&p=0x%x\012 p=0x%x\012\000"
	.align	2
.LC4:
	.ascii	"&str=0x%x\012 str=0x%x\012\000"
	.align	2
.LC5:
	.ascii	"main=0x%x\012 &foo=0x%x\012\000"
	.text
	.align	2
	.global	main
	.type	main, %function
main:
	@ args = 0, pretend = 0, frame = 16
	@ frame_needed = 1, uses_anonymous_args = 0
	stmfd	sp!, {fp, lr}
	add	fp, sp, #4
	sub	sp, sp, #16
	mov	r0, #4
	bl	malloc
	mov	r3, r0
	str	r3, [fp, #-12]
	ldr	r3, .L5
	str	r3, [fp, #-16]
	ldr	r2, .L5+4
	sub	r3, fp, #8
	mov	r0, r2
	mov	r1, r3
	ldr	r2, .L5+8
	bl	printf
	ldr	r1, .L5+12
	ldr	r3, [fp, #-12]
	sub	r2, fp, #12
	mov	r0, r1
	mov	r1, r2
	mov	r2, r3
	bl	printf
	ldr	r1, .L5+16
	ldr	r3, [fp, #-16]
	sub	r2, fp, #16
	mov	r0, r1
	mov	r1, r2
	mov	r2, r3
	bl	printf
	bl	foo
	ldr	r3, .L5+20
	mov	r0, r3
	ldr	r1, .L5+24
	ldr	r2, .L5+28
	bl	printf
	mov	r0, r3
	sub	sp, fp, #4
	ldmfd	sp!, {fp, pc}
.L6:
	.align	2
.L5:
	.word	.LC1
	.word	.LC2
	.word	c.2227
	.word	.LC3
	.word	.LC4
	.word	.LC5
	.word	main
	.word	foo
	.size	main, .-main
	.local	c.2227
	.comm	c.2227,4,4
	.data
	.align	2
	.type	e.2223, %object
	.size	e.2223, 4
e.2223:
	.word	5
	.ident	"GCC: (Debian 4.6.3-14+rpi1) 4.6.3"
	.section	.note.GNU-stack,"",%progbits
