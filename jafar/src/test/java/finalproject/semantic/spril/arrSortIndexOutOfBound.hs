import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 8) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 8) regA 
    , Push regA 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 5) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 235) 
    , Nop 
    , Load (ImmValue 8) regA 
    , Push regA 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (DirAddr 7) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 271) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 271) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (DirAddr 7) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 149) 
    , Nop 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 271) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 271) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 271) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 271) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute GtE regA regB regA 
    , Push regA 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 271) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 271) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (DirAddr 8) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Gt regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute And regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 87) 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (DirAddr 6) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 271) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 271) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (DirAddr 8) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (DirAddr 7) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 7) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 47) 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , EndProg 
    ]

main = run [prog0]

