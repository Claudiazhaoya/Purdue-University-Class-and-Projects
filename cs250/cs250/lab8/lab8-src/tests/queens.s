 # Reserve space
	.data
queens:
	.long 0
	.long 0

 # Reserve space
	.data
solid:
	.long 0
	.long 0

	.text
.globl abs
abs:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
if_start_0:
	#Push Local var v
	movq 128(%rsp), %rbx

	# push 0
	movq $0,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je if_end_0

	# push -1
	movq $-1,%rbx
	#Push Local var v
	movq 128(%rsp), %r10

	# *
	imulq %r10,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_0
if_end_0:
else_end_0:
	#Push Local var v
	movq 128(%rsp), %rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl check
check:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)

	# FOR START
twirl_start_1:
	#Push Local var i
	movq 120(%rsp), %rbx
	#Push Local var depth
	movq 128(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_1
	jmp for_assign_1
for_expression_1:
continue_point_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp twirl_start_1
for_assign_1:
if_start_2:
	#Push Local var i
	movq 120(%rsp), %rbx
	#Assign2 to Global var queens
	movq queens(%rip), %rax
	movq %rbx, %r12
	imulq $8, %rbx
	addq %rbx, %rax
	movq (%rax), %rbx
	movq $0, %r12
	#Push Local var depth
	movq 128(%rsp), %r10
	#Assign2 to Global var queens
	movq queens(%rip), %rax
	movq %r10, %r12
	imulq $8, %r10
	addq %r10, %rax
	movq (%rax), %r10
	movq $0, %r12

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx
	#Push Local var i
	movq 120(%rsp), %r10
	#Assign2 to Global var queens
	movq queens(%rip), %rax
	movq %r10, %r12
	imulq $8, %r10
	addq %r10, %rax
	movq (%rax), %r10
	movq $0, %r12
	#Push Local var depth
	movq 128(%rsp), %r13
	#Assign2 to Global var queens
	movq queens(%rip), %rax
	movq %r13, %r12
	imulq $8, %r13
	addq %r13, %rax
	movq (%rax), %r13
	movq $0, %r12

	# -
	subq %r13,%r10
     # func=abs nargs=1
     # Move values from reg stack to reg args
	movq %r10, %rdi
	call abs
	movq %rax, %r10
	#Push Local var depth
	movq 128(%rsp), %r13
	#Push Local var i
	movq 120(%rsp), %r14

	# -
	subq %r14,%r13

	# ==
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmove %rdi, %r10

	# ||
	orq %rbx,%r10
	movq %r10,%rbx
	cmpq $0, %rbx
	je if_end_2

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_2
if_end_2:
else_end_2:
	jmp for_expression_1
twirl_end_1:

	# FOR END

	# push 1
	movq $1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl bruteforce
bruteforce:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
if_start_3:
	#Push Local var depth
	movq 128(%rsp), %rbx

	# push 8
	movq $8,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_3
	#top=0

	# push string "Solution #%2ld = [ " top=0
	movq $string0, %rbx
	movq solid,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	movq solid,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var solid
	movq %rbx, solid(%rip)

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)

	# FOR START
twirl_start_4:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 8
	movq $8,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_4
	jmp for_assign_4
for_expression_4:
continue_point_1:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp twirl_start_4
for_assign_4:
	#top=0

	# push string "%ld " top=0
	movq $string1, %rbx
	#Push Local var i
	movq 120(%rsp), %r10
	#Assign2 to Global var queens
	movq queens(%rip), %rax
	movq %r10, %r12
	imulq $8, %r10
	addq %r10, %rax
	movq (%rax), %r10
	movq $0, %r12

	# push 1
	movq $1,%r13

	# +
	addq %r13,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_4
twirl_end_4:

	# FOR END
	#top=0

	# push string "]\n" top=0
	movq $string2, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_3
if_end_3:
else_end_3:

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)

	# FOR START
twirl_start_5:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 8
	movq $8,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_5
	jmp for_assign_5
for_expression_5:
continue_point_2:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp twirl_start_5
for_assign_5:
	#Push Local var depth
	movq 128(%rsp), %rbx
	#Push Local var i
	movq 120(%rsp), %r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var queens
	movq queens(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)
if_start_6:
	#Push Local var depth
	movq 128(%rsp), %rbx
     # func=check nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call check
	movq %rax, %rbx

	# push 0
	movq $0,%r10

	# !=
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovne %rdi, %rbx
	cmpq $0, %rbx
	je if_end_6
	#Push Local var depth
	movq 128(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
     # func=bruteforce nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call bruteforce
	movq %rax, %rbx
	jmp else_end_6
if_end_6:
else_end_6:
	jmp for_expression_5
twirl_end_5:

	# FOR END

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl main
main:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments

	# push 8
	movq $8,%rbx

	# push 8
	movq $8,%r10

	# *
	imulq %r10,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Global var queens
	movq %rbx, queens(%rip)

	# push 1
	movq $1,%rbx
	#Assign to Global var solid
	movq %rbx, solid(%rip)

	# push 0
	movq $0,%rbx
     # func=bruteforce nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call bruteforce
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "Solution #%2ld = [ "

string1:
	.string "%ld "

string2:
	.string "]\n"

