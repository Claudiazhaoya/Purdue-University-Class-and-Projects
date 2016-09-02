	.data
	.comm	a,8

	.text
format1:
	.string"sort\n"		#prompt for input

format2:
	.string"%d"		#format for scanf

format3:
	.string "sorted\n"	#string for sorted
format4:
	.string"%d\n"		#format for output

.global main
main:
	pushq %rbx	#push rbx onto stack
	pushq %r10	#push r10 to stack
	pushq %r13	#push	r13 to stack
	pushq %r14	#push r14 to stack
	pushq %r15	#push r15 to stack
	movq	$0,%rbx	#put zero into rbx
	movq	$format1, %rdi	#put format 1 into rdi
	movq	$0,%rax
	call	printf	#print format1
	movq	$1,%r10	
	jmp input	#jump to input function

input:
	movq	$format2,	%rdi	#puts format two into rdi
	movq	%rsp, %rsi	#sets rsi to rsp
	movq	$0,	%rax	#sets rax to zero
	call	scanf		#scans for number

	cmp	$-1,%eax	#compares input for null termenator
	je	sort1		#if it is go to sort function

	addq	$1,%rbx		#add 1 to rbx
	subq	$8,%rsp		#move the stack pointer by 8
	jmp	input		#jump to input



sort1:
	movq	%rbx,%r13	#sets r13 to rbx
	cmpq	$0,%r10		#compares 0  to r10 to check for sort
	je	output		#if true go to output
	movq	$0,%r10		#set r10 to 0
	
	movq	%rsp,%r14	#put rsp into r14
	movq	%rsp,%r15	#pur rsp into r15
	addq	$8,%r15		#make r15 a stack pointer poistion lareger than r14
	jmp	sort2		#jump to sort2 funtion
sort2:
	cmpq	$0,%r13		#sets r13 to zero
	addq	$8,%r14		#adds  8 to r14
	addq	$8,%r15		#adds 8 to r15
	subq	$1,%r13		#subtrats 1 from r13
	jle	sort1		#if less then go to sort1
	jmp	sort3		#jump to sort 3

sort3:
	movq	(%r14),%r8	#sets r8 equal to value of r14
	movq	(%r15),%r9	#sets r9 equal to value of r15
	cmpq %r8,%r9		#compare r8 and 9
	jle	swap		#if less then swap
	jmp	sort2		#else go to swap 2

swap:
	movq	%r8,(%r15)	#put r8 into r15
	movq	%r9,(%r14)	#put r9 into r14
	movq	$1,%r10		#set r10 to 1 to show swap was done
	jmp	sort2		#go to swqap two
	
output:
	cmpq	$0,%rbx		#compare 0 and rbx
	jle	printout	#if true go to prrintout
	movq	$format4,%rdi	#put format4 into rdi
	movl 	8(%rsp), %esi	#move value of rsp plus 8 to esi
	movq	$0,%rax		#put zero in raz
	call	printf		#print

	addq	$8,%rsp		#add 8 to rsp
	subq	$1,%rbx		#subtract 1 from rbx
	jmp	output		#jump to output

printout:
	popq	%r15		#pop r15
	popq	%r14		#pop r14
	popq	%r13		#pop r13
	popq	%r10		#pop r10
 	popq	%rbx		#pop rbx
	ret			#return
