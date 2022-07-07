import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 11) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 10) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 9) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 8) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 7) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 6) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 5) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
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
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 165) 
    , Nop 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (DirAddr 11) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 9) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 221) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 221) 
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
    , Load (DirAddr 11) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 9) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 221) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 221) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (DirAddr 11) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 9) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 221) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 221) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (DirAddr 11) regA 
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
    , Load (DirAddr 11) regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 77) 
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

