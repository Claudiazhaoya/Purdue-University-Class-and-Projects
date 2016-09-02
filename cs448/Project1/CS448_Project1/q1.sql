SELECT EmpId FROM(	
	SELECT EmpId, count(distinct ProjId) as num FROM EmpProject WHERE EmpID IN
		(SELECT EmpId FROM EmpProject WHERE EmpId NOT IN
			(SELECT distinct MgrId FROM ProjectManager
			)
		) GROUP BY EmpID
	)WHERE num = (SELECT count (distinct ProjId) FROM Project);
			